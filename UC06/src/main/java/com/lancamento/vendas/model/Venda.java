package com.lancamento.vendas.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Venda {

	@Id
	private Long id;
	private Long produtoId;
	private int quantidadeVendida;
	private double valorTotalVenda;
	private Date dataHoraVenda;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}

	public int getQuantidadeVendida() {
		return quantidadeVendida;
	}

	public void setQuantidadeVendida(int quantidadeVendida) {
		this.quantidadeVendida = quantidadeVendida;
	}

	public double getValorTotalVenda() {
		return valorTotalVenda;
	}

	public void setValorTotalVenda(double valorTotalVenda) {
		this.valorTotalVenda = valorTotalVenda;
	}

	public Date getDataHoraVenda() {
		return dataHoraVenda;
	}

	public void setDataHoraVenda(Date dataHoraVenda) {
		this.dataHoraVenda = dataHoraVenda;
	}
}
