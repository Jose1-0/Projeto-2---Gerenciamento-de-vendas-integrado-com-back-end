package com.lancamento.vendas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lancamento.vendas.model.Venda;
import com.lancamento.vendas.service.ProdutoService;

@RestController
@RequestMapping("/api/vendas")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // Realizar a venda
    @PostMapping("/realizar-venda")
    public Venda realizarVenda(@RequestBody Venda venda) {
        try {
            return produtoService.realizarVenda(venda);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao realizar a venda: " + e.getMessage());
        }
    }
}
