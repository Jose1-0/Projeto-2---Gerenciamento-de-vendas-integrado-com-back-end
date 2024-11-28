package com.lancamento.vendas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemVenda {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private Long produtoId;
	    private String nomeProduto;
	    private double precoVenda;
	    private int quantidade;
	    private double totalItem;

	    @ManyToOne
	    @JoinColumn(name = "venda_id", nullable = false)
	    private Venda venda;

	    // Getters e Setters
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

	    public String getNomeProduto() {
	        return nomeProduto;
	    }

	    public void setNomeProduto(String nomeProduto) {
	        this.nomeProduto = nomeProduto;
	    }

	    public double getPrecoVenda() {
	        return precoVenda;
	    }

	    public void setPrecoVenda(double precoVenda) {
	        this.precoVenda = precoVenda;
	    }

	    public int getQuantidade() {
	        return quantidade;
	    }

	    public void setQuantidade(int quantidade) {
	        this.quantidade = quantidade;
	    }

	    public double getTotalItem() {
	        return totalItem;
	    }

	    public void setTotalItem(double totalItem) {
	        this.totalItem = totalItem;
	    }

	    public Venda getVenda() {
	        return venda;
	    }

	    public void setVenda(Venda venda) {
	        this.venda = venda;
	    }
}