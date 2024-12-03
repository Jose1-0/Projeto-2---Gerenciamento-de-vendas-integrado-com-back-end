package com.lancamento.vendas.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lancamento.vendas.model.ItemVenda;
import com.lancamento.vendas.model.Produto;
import com.lancamento.vendas.model.Venda;
import com.lancamento.vendas.repository.ProdutoRepository;
import com.lancamento.vendas.repository.VendaRepository;
import com.lancamento.vendas.service.ProdutoService;
import com.lancamento.vendas.service.VendaService;

@RestController
@RequestMapping("/api/vendas")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;
    
    @Autowired
    private VendaService vendaService;
    
    @Autowired
    private VendaRepository vendaRepository;
    
    @Autowired
    private ProdutoRepository produtoRepository;

    // Endpoint para listar todos os produtos
    @GetMapping("/listar")
    public List<Produto> listarProdutos() {
        return produtoService.listarTodos();
    }

    // Endpoint para obter quantidades mensais de vendas
    @GetMapping("/quantidades-mensais")
    public List<Map<String, Object>> obterQuantidadesMensais() {
        try {
            List<Map<String, Object>> quantidadesMensais = vendaService.calcularQuantidadesMensais();

            // Verificar se alguma chave é null e filtrar ou corrigir isso
            if (quantidadesMensais == null || quantidadesMensais.isEmpty()) {
                throw new RuntimeException("Nenhum dado encontrado.");
            }

            // Corrigir valores null antes de retornar
            quantidadesMensais = quantidadesMensais.stream()
                .filter(item -> item.get("mes") != null)  // Supondo que a chave seja "mes" ou outra relevante
                .collect(Collectors.toList());

            return quantidadesMensais;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter quantidades mensais: " + e.getMessage());
        }
    }


    // Endpoint para obter vendas mensais
    @GetMapping("/vendas-mensais")
    public List<Map<String, Object>> obterVendasMensais() {
        try {
            return vendaService.calcularVendasMensais();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter vendas mensais: " + e.getMessage());
        }
    }

 // Novo endpoint para obter o relatório mensal para gráfico de linha
    @GetMapping("/relatorio-mensal")
    public ResponseEntity<Map<String, Map<String, Double>>> obterRelatorioMensal(@RequestBody Map<String, String> periodo) {
        try {
            String dataInicial = periodo.get("dataInicial");
            String dataFinal = periodo.get("dataFinal");

            // Converter strings para Date, assumindo que a data vem no formato "yyyy-MM-dd"
            Date dataInicio = new SimpleDateFormat("yyyy-MM-dd").parse(dataInicial);
            Date dataFim = new SimpleDateFormat("yyyy-MM-dd").parse(dataFinal);

            // Buscar as vendas no intervalo de tempo
            List<Venda> vendas = vendaRepository.findByDataHoraVendaBetween(dataInicio, dataFim);

            // Agrupar as vendas por mês e calcular o total de vendas e o custo total
            Map<String, Map<String, Double>> vendasMensais = vendas.stream()
                .collect(Collectors.groupingBy(
                    venda -> new SimpleDateFormat("yyyy-MM").format(venda.getDataHoraVenda()), 
                    Collectors.collectingAndThen(
                        Collectors.toList(), 
                        listaDeVendas -> {
                            double totalVenda = 0;
                            double totalCusto = 0;

                            // Para cada venda, calcular o totalVenda e o totalCusto
                            for (Venda venda : listaDeVendas) {
                                for (ItemVenda item : venda.getItens()) {
                                    totalVenda += item.getTotalItem();
                                    
                                    // Corrigir o acesso ao produtoId, utilizando o objeto Produto
                                    Produto produto = item.getProduto();  // acesso ao produto diretamente
                                    if (produto != null) {
                                        totalCusto += produto.getPrecoCusto() * item.getQuantidade();
                                    }
                                }
                            }

                            // Criar o mapa com o resumo dos totais
                            Map<String, Double> resumo = new HashMap<>();
                            resumo.put("totalVenda", totalVenda);
                            resumo.put("totalCusto", totalCusto);
                            return resumo;
                        }
                    )
                ));

            // Retornar o mapa com os totais por mês
            return ResponseEntity.ok(vendasMensais);
        } catch (Exception e) {
            // Se ocorrer um erro, retornar um erro 500 com a mensagem de exceção
            Map<String, Map<String, Double>> errorResponse = new HashMap<>();
            Map<String, Double> errorMap = new HashMap<>();
            errorMap.put("error", 1.0);  // ou qualquer outro valor que represente um erro
            errorResponse.put("error", errorMap);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    
    @PostMapping("/realizar")
    public ResponseEntity<Map<String, Object>> realizarVenda(@RequestBody Venda venda) {
        double totalVenda = 0.0;

        for (ItemVenda item : venda.getItens()) {
            Produto produto = produtoRepository.findById(item.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + item.getProdutoId()));

            // Verificar estoque
            if (produto.getQuantidade() < item.getQuantidade()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Estoque insuficiente para o produto: " + produto.getNome()));
            }

            // Atualizar estoque do produto
            produto.setQuantidade(produto.getQuantidade() - item.getQuantidade());
            produtoRepository.save(produto);

            // Calcular valores do item e venda total
            item.setNomeProduto(produto.getNome());
            item.setPrecoVenda(produto.getPrecoVenda());
            item.setTotalItem(item.getQuantidade() * produto.getPrecoVenda());
            item.setVenda(venda);
            totalVenda += item.getTotalItem();
        }

        // Atualizar dados gerais da venda
        venda.setDataHoraVenda(new Date());
        venda.setQuantidadeVendida(venda.getItens().stream().mapToInt(ItemVenda::getQuantidade).sum());
        venda.setValorTotalVenda(totalVenda);

        vendaRepository.save(venda);

        // Retornar um JSON com a mensagem de sucesso e o total da venda
        return ResponseEntity.ok(Map.of(
            "message", "Venda realizada com sucesso!",
            "totalVenda", totalVenda
        ));
    }




   




}
