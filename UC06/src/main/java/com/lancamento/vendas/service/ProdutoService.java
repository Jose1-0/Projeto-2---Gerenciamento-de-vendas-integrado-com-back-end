package com.lancamento.vendas.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lancamento.vendas.model.Produto;
import com.lancamento.vendas.model.Venda;
import com.lancamento.vendas.repository.ProdutoRepository;
import com.lancamento.vendas.repository.VendaRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private VendaRepository vendaRepository;
    
    
    
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }
    
    
    
    
    
    
    
    
    
    

//    // Verificar se o produto existe
//    public Produto verificarProdutoExistente(Long produtoId) throws Exception {
//        Optional<Produto> produtoOpt = produtoRepository.findById(produtoId);
//        if (!produtoOpt.isPresent()) {
//            throw new Exception("Produto não encontrado! ID: " + produtoId);
//        }
//        return produtoOpt.get();
//    }
//
//    // Verificar se o estoque é suficiente
//    public void verificarEstoqueSuficiente(Produto produto, int quantidadeVendida) throws Exception {
//        if (produto.getQuantidade() <= quantidadeVendida) {
//            throw new Exception("Estoque insuficiente para o produto: " + produto.getNome());
//        }
//    }
//
//    // Calcular o valor total da venda
//    public double calcularValorVenda(Produto produto, int quantidadeVendida) {
//        return produto.getPrecoVenda() * quantidadeVendida;
//    }
//
//    // Criar a venda
//    public Venda criarVenda(Long produtoId, int quantidadeVendida, double valorTotalVenda) {
//        Venda venda = new Venda();
//        venda.setProdutoId(produtoId);
//        venda.setQuantidadeVendida(quantidadeVendida);
//        venda.setValorTotalVenda(valorTotalVenda);
//        venda.setDataHoraVenda(new Date());
//        return venda;
//    }
//
//    // Atualizar o estoque do produto
//    public void atualizarEstoque(Produto produto, int quantidadeVendida) {
//        produto.setQuantidade(produto.getQuantidade() - quantidadeVendida);
//        produtoRepository.save(produto);
//    }
//
//    public Venda realizarVenda(Venda venda) throws Exception {
//        Produto produto = verificarProdutoExistente(venda.getProdutoId());
//        verificarEstoqueSuficiente(produto, venda.getQuantidadeVendida());
//
//        double valorTotalVenda = calcularValorVenda(produto, venda.getQuantidadeVendida());
//
//        venda.setValorTotalVenda(valorTotalVenda);
//        venda.setDataHoraVenda(new Date());
//
//        vendaRepository.save(venda);
//
//        atualizarEstoque(produto, venda.getQuantidadeVendida());
//
//        return venda;
//    }
    

}
