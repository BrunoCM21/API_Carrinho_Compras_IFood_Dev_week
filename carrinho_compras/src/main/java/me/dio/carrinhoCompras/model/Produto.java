package me.dio.carrinhoCompras.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

import javax.persistence.*;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private double valorUnitario;

    @Builder.Default
    private Boolean disponivel = true;

    @ManyToOne
    @JsonIgnore
    private Restaurante restaurante;

    public Produto() {
    }

    public Produto(Long id, String nome, double valorUnitario, Boolean disponivel, Restaurante restaurante) {
        this.id = id;
        this.nome = nome;
        this.valorUnitario = valorUnitario;
        this.disponivel = disponivel;
        this.restaurante = restaurante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
}
