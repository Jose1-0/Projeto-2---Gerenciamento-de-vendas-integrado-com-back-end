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
    
    // Relacionamento ManyToOne com Produto
    @ManyToOne
    @JoinColumn(name = "produto_id")  // O campo "produto_id" na tabela do banco de dados
    private Produto produto;  // Agora, você referencia diretamente o objeto Produto

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

    // Método para acessar o produto associado
    public Produto getProduto() {
        return produto;
    }

    // Método para configurar o produto associado
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }
    
 // Método para acessar o produtoId
    public Long getProdutoId() {
        return produto != null ? produto.getId() : null;  // Retorna o ID do produto, se o produto não for nulo
    }

    // Método setNomeProduto
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

    // Método setTotalItem
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
