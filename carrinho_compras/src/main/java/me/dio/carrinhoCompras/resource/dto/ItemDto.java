package me.dio.carrinhoCompras.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Embeddable
public class ItemDto {

    private Long idProduto;
    private int quantidade;
    private Long idSacola;
}
