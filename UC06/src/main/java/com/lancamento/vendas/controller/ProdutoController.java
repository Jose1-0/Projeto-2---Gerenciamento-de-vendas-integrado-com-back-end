package com.lancamento.vendas.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    VendaRepository vendaRepository;
    
    @Autowired
    ProdutoRepository produtoRepository;
    
    
    @GetMapping("/listar")
    public List<Produto> listarProdutos() {
        return produtoService.listarTodos();
    }

    
    @GetMapping("/quantidades-mensais")
    public List<Map<String, Object>> obterQuantidadesMensais() {
        try {
            return vendaService.calcularQuantidadesMensais();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter quantidades mensais: " + e.getMessage());
        }
    }
    
    @GetMapping("/vendas-mensais")
    public List<Map<String, Object>> obterVendasMensais() {
        try {
            return vendaService.calcularVendasMensais();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter vendas mensais: " + e.getMessage());
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

    // Endpoint para verificar se o produto existe
//    @GetMapping("/verificar-produto/{produtoId}")
//    public Produto verificarProdutoExistente(@PathVariable Long produtoId) {
//        try {
//            return produtoService.verificarProdutoExistente(produtoId);
//        } catch (Exception e) {
//            throw new RuntimeException("Erro ao verificar produto: " + e.getMessage());
//        }
//    }
//
//    // Endpoint para verificar se o estoque é suficiente
//    @GetMapping("/verificar-estoque/{produtoId}/{quantidadeVendida}")
//    public String verificarEstoqueSuficiente(@PathVariable Long produtoId, @PathVariable int quantidadeVendida) {
//        try {
//            Produto produto = produtoService.verificarProdutoExistente(produtoId);
//            produtoService.verificarEstoqueSuficiente(produto, quantidadeVendida);
//            return "Estoque suficiente para o produto: " + produto.getNome();
//        } catch (Exception e) {
//            return "Erro: " + e.getMessage();
//        }
//    }
//
//    // Endpoint para calcular o valor da venda
//    @GetMapping("/calcular-valor-venda/{produtoId}/{quantidadeVendida}")
//    public double calcularValorVenda(@PathVariable Long produtoId, @PathVariable int quantidadeVendida) {
//        try {
//            Produto produto = produtoService.verificarProdutoExistente(produtoId);
//            return produtoService.calcularValorVenda(produto, quantidadeVendida);
//        } catch (Exception e) {
//            throw new RuntimeException("Erro ao calcular valor da venda: " + e.getMessage());
//        }
//    }
//
//    // Endpoint para criar a venda
//    @PostMapping("/criar-venda")
//    public Venda criarVenda(@RequestBody Venda venda) {
//        try {
//            Produto produto = produtoService.verificarProdutoExistente(venda.getProdutoId());
//            double valorTotalVenda = produtoService.calcularValorVenda(produto, venda.getQuantidadeVendida());
//            return produtoService.criarVenda(venda.getProdutoId(), venda.getQuantidadeVendida(), valorTotalVenda);
//        } catch (Exception e) {
//            throw new RuntimeException("Erro ao criar a venda: " + e.getMessage());
//        }
//    }
//
//    // Endpoint para atualizar o estoque
//    @PostMapping("/atualizar-estoque")
//    public String atualizarEstoque(@RequestBody Venda venda) {
//        try {
//            Produto produto = produtoService.verificarProdutoExistente(venda.getProdutoId());
//            produtoService.atualizarEstoque(produto, venda.getQuantidadeVendida());
//            return "Estoque atualizado com sucesso para o produto: " + produto.getNome();
//        } catch (Exception e) {
//            return "Erro ao atualizar o estoque: " + e.getMessage();
//        }
//    }
//
//    // Endpoint para realizar a venda completa
//    @PostMapping("/realizar-venda")
//    public Venda realizarVenda(@RequestBody Venda venda) {
//        try {
//            return produtoService.realizarVenda(venda);
//        } catch (Exception e) {
//            throw new RuntimeException("Erro ao realizar a venda: " + e.getMessage());
//        }
//    }
}
