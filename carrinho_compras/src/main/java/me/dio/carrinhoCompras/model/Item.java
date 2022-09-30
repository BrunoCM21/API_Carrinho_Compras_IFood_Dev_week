package me.dio.carrinhoCompras.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Produto produto;

    private int quantidade;

    @ManyToOne
    @JsonIgnore
    private Sacola sacola;

    public Item() {
    }

    public Item(Long id, Produto produto, int quantidade, Sacola sacola) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.sacola = sacola;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Sacola getSacola() {
        return sacola;
    }

    public void setSacola(Sacola sacola) {
        this.sacola = sacola;
    }
}
