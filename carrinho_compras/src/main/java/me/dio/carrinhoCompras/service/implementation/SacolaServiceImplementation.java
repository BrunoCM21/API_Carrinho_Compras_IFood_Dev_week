package me.dio.carrinhoCompras.service.implementation;

import lombok.RequiredArgsConstructor;
import me.dio.carrinhoCompras.enumeration.FormaPagamento;
import me.dio.carrinhoCompras.model.Item;
import me.dio.carrinhoCompras.model.Restaurante;
import me.dio.carrinhoCompras.model.Sacola;
import me.dio.carrinhoCompras.repository.ItemRepository;
import me.dio.carrinhoCompras.repository.ProdutoRepository;
import me.dio.carrinhoCompras.repository.SacolaRepository;
import me.dio.carrinhoCompras.resource.SacolaResource;
import me.dio.carrinhoCompras.resource.dto.ItemDto;
import me.dio.carrinhoCompras.service.SacolaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SacolaServiceImplementation implements SacolaService {

    private final SacolaRepository sacolaRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemRepository itemRepository;
    @Override
    public Sacola verSacola(Long id) {
//      Forma que eu fiz
//      try {
//          Optional<Sacola> itemOptional = sacolaRepository.findById(id);
//          if(itemOptional.isPresent()) {
//              return itemOptional.get();
//          }
//      } catch (NullPointerException npe) {
//          throw new RuntimeException("Sacola não encontrada.");
//      }
//      return null;

//      Forma que a Camila fez
        return sacolaRepository.findById(id).orElseThrow(() -> {
           throw new RuntimeException("Sacola não encontrada.");
        });
    }

    @Override
    public Sacola finalizarSacola(Long id, int formaPagamento) {
        Sacola sacolaEncontrada = verSacola(id);
        if(sacolaEncontrada.getItens().isEmpty()) {
            throw new RuntimeException("Inclua itens na sacola.");
        }
        FormaPagamento fp = formaPagamento == 0 ? FormaPagamento.DINHEIRO : FormaPagamento.MAQUININHA;
        sacolaEncontrada.setFormaPagamento(fp);
        sacolaEncontrada.setFinalizada(true);
        return sacolaRepository.save(sacolaEncontrada);
    }

    @Override
    public Item incluirItem(ItemDto itemDto) {
        Sacola sacolaEncontrada = verSacola(itemDto.getIdSacola());
        if(sacolaEncontrada.isFinalizada()) {
            throw new RuntimeException("Sacola já finalizada.");
        }
        Item item = Item.builder()
                .quantidade(itemDto.getQuantidade())
                .sacola(sacolaEncontrada)
                .produto(produtoRepository.findById(itemDto.getIdProduto()).orElseThrow(() -> {
                    throw new RuntimeException("Produto não encontrada.");
                })).build();

        List<Item> itens = sacolaEncontrada.getItens();
        if(itens.isEmpty()) {
            itens.add(item);
        } else {
            Restaurante restauranteAtual = itens.get(0).getProduto().getRestaurante();
            Restaurante restauranteProdutoInserindo = item.getProduto().getRestaurante();
            if(restauranteAtual.getId() == restauranteProdutoInserindo.getId()) {
                itens.add(item);
            } else {
                throw new RuntimeException("Não é possível adicionar itens de Restaurantes diferentes.");
            }
        }

        List<Double> valorItens = new ArrayList<Double>();
        for(Item itemInterno : itens) {
            Double valorTotalItem = itemInterno.getProduto().getValorUnitario() * itemInterno.getQuantidade();
            valorItens.add(valorTotalItem);
        }

//      double valorTotalSacola = valorItens.stream().mapToDouble(valorItem -> valorItem).sum();

        Double valorSomaTotal = 0.0;
        for(Double valor : valorItens){
            valorSomaTotal += valor;
        }
        sacolaEncontrada.setValorTotal(valorSomaTotal);
        sacolaRepository.save(sacolaEncontrada);
        return itemRepository.save(item);
    }
}
