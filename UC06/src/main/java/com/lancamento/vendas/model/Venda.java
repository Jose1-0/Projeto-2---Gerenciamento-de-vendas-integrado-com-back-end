package com.lancamento.vendas.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Venda {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "produto_id")  // Mapeando a coluna produto_id para a propriedade produtoId
    private Long produtoId;

    @Column(name = "quantidade_vendida")  // Mapeando a coluna quantidade_vendida para a propriedade quantidadeVendida
    private int quantidadeVendida;

    @Column(name = "valor_total_venda")  // Mapeando a coluna valor_total_venda para a propriedade valorTotalVenda
    private double valorTotalVenda;

    @Column(name = "data_hora_venda")  // Mapeando a coluna data_hora_venda para a propriedade dataHoraVenda
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
