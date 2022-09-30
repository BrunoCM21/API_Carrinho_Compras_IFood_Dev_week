package me.dio.carrinhoCompras.service;

import me.dio.carrinhoCompras.resource.dto.ItemDto;
import me.dio.carrinhoCompras.model.Item;
import me.dio.carrinhoCompras.model.Sacola;

public interface SacolaService {
    Sacola verSacola(Long id);
    Sacola finalizarSacola(Long id, int formaPagamento);
    Item incluirItem(ItemDto itemDto);
}
