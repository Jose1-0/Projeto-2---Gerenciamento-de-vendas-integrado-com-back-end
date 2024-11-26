package com.lancamento.vendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lancamento.vendas.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
