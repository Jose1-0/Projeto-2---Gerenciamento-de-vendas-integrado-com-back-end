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
            .filter(venda -> venda.getQuantidadeVendida() > 0) // Ignora vendas com quantidade 0
            .collect(Collectors.groupingBy(venda -> {
                // Verifica se a dataHoraVenda é nula
                if (venda.getDataHoraVenda() == null) {
                    return "Data Inválida"; // Ou qualquer valor padrão para data inválida
                }
                // Convertendo Date para LocalDate e formatando para "yyyy-MM"
                LocalDate localDate = venda.getDataHoraVenda().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));
            },
            Collectors.groupingBy(venda -> {
                // Verifica se o produtoId é nulo e trata isso adequadamente
                if (venda.getProdutoId() == null) {
                    return -1L; // Ou qualquer valor padrão para indicar que o produto é inválido
                }
                return venda.getProdutoId();
            },
            Collectors.summingDouble(Venda::getQuantidadeVendida))));

        // Convertendo para uma lista de mapas para o retorno
        List<Map<String, Object>> resultado = new ArrayList<>();
        quantidadesMensais.forEach((mes, produtos) -> {
            Map<String, Object> mesData = new HashMap<>();
            mesData.put("mes", mes);
            produtos.forEach((produtoId, quantidadeVendida) -> {
                Map<String, Object> produtoData = new HashMap<>();
                produtoData.put("produtoId", produtoId);
                produtoData.put("quantidadeVendida", quantidadeVendida);

                // Se o produtoId for -1, isso indica que não foi possível associar um produto válido
                if (produtoId == -1L) {
                    produtoData.put("produtoNome", "Produto Inválido");
                } else {
                    produtoData.put("produtoNome", "Produto com ID " + produtoId); // Aqui você pode consultar o nome do produto, se necessário
                }

                mesData.put("produto_" + produtoId, produtoData);
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
