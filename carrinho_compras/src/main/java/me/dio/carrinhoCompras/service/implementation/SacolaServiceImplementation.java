package me.dio.carrinhoCompras.service.implementation;

import lombok.RequiredArgsConstructor;
import me.dio.carrinhoCompras.enumeration.FormaPagamento;
import me.dio.carrinhoCompras.model.Item;
import me.dio.carrinhoCompras.model.Sacola;
import me.dio.carrinhoCompras.repository.SacolaRepository;
import me.dio.carrinhoCompras.resource.SacolaResource;
import me.dio.carrinhoCompras.resource.dto.ItemDto;
import me.dio.carrinhoCompras.service.SacolaService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SacolaServiceImplementation implements SacolaService {

    private final SacolaRepository sacolaRepository;
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
        return null;
    }
}
