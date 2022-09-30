package me.dio.carrinhoCompras.resource;

import lombok.RequiredArgsConstructor;
import me.dio.carrinhoCompras.model.Item;
import me.dio.carrinhoCompras.model.Sacola;
import me.dio.carrinhoCompras.resource.dto.ItemDto;
import me.dio.carrinhoCompras.service.SacolaService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/devWeek/sacolas")
public class SacolaResource {

    private final SacolaService sacolaService;

    @PostMapping
    public Item incluirItem(@RequestBody ItemDto itemDto) {
        return sacolaService.incluirItem(itemDto);
    }

    @GetMapping("/{id}")
    public Sacola verSacola(@PathVariable("id") Long id) {
        return sacolaService.verSacola(id);
    }

    @PatchMapping("/finalizarSacola/{idSacola}")
    public Sacola finalizarSacola(@PathVariable("idSacola") Long idSacola,@RequestParam("formaPgto") int formaPgto) {
        return sacolaService.finalizarSacola(idSacola, formaPgto);
    }
}
