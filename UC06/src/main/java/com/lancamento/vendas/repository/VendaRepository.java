package com.lancamento.vendas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lancamento.vendas.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long>{
	List<Venda> saveAll(List<Venda> vendas);
}
