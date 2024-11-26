package com.lancamento.vendas.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lancamento.vendas.model.Venda;
import com.lancamento.vendas.repository.VendaRepository;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    public List<Map<String, Object>> calcularQuantidadesMensais() {
        List<Venda> vendas = vendaRepository.findAll();

        // Agrupando as vendas por mês e produto
        Map<String, Map<Long, Double>> quantidadesMensais = vendas.stream()
            .collect(Collectors.groupingBy(venda -> {
                // Convertendo Date para LocalDate e formatando para "yyyy-MM"
                LocalDate localDate = venda.getDataHoraVenda().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));
            },
            Collectors.groupingBy(venda -> venda.getProdutoId(),
            Collectors.summingDouble(Venda::getQuantidadeVendida))));

        // Convertendo para uma lista de mapas para o retorno
        List<Map<String, Object>> resultado = new ArrayList<>();
        quantidadesMensais.forEach((mes, produtos) -> {
            Map<String, Object> mesData = new HashMap<>();
            mesData.put("mes", mes);
            produtos.forEach((produtoId, quantidade) -> {
                mesData.put("produtoId", produtoId);
                mesData.put("quantidadeVendida", quantidade);
            });
            resultado.add(mesData);
        });

        return resultado;
    }
    
    public List<Map<String, Object>> calcularVendasMensais() {
        List<Venda> vendas = vendaRepository.findAll();
        
        // Agrupando as vendas por mês
        Map<String, Double> vendasMensais = vendas.stream()
            .collect(Collectors.groupingBy(venda -> {
                // Formato do mês: "yyyy-MM"
                return venda.getDataHoraVenda().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString().substring(0, 7);
            }, 
            Collectors.summingDouble(Venda::getValorTotalVenda)));  // Usando o método getValorTotalVenda()

        // Convertendo o mapa para uma lista de mapas para o retorno
        List<Map<String, Object>> resultado = new ArrayList<>();
        vendasMensais.forEach((mes, valorTotal) -> {
            Map<String, Object> mesData = new HashMap<>();
            mesData.put("mes", mes);
            mesData.put("valorVenda", valorTotal);
            resultado.add(mesData);
        });

        return resultado;
    }

}
