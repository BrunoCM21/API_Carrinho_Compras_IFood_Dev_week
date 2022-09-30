package me.dio.carrinhoCompras.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * Anotações do Lombok que traz - em ordem - construtora com todos os argumentos,
 *          Builder é design pattern, Data gera getters e setters, Hibernate com atributos lazy e construtora sem argumentos
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Embeddable
public class Endereco {
    private String cep;
    private String complemento;
}
