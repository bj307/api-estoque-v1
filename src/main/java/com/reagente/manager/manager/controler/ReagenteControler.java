package com.reagente.manager.manager.controler;

import com.reagente.manager.manager.controler.dto.ReagenteDTO;
import com.reagente.manager.manager.entity.Category;
import com.reagente.manager.manager.entity.Reagente;
import com.reagente.manager.manager.repository.ReagenteRepository;
import com.reagente.manager.manager.service.CategoryService;
import com.reagente.manager.manager.service.ReagenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/reagente")
@RequiredArgsConstructor
public class ReagenteControler {

    private final ReagenteRepository repository;
    private final CategoryService categoryService;

    @Autowired
    ReagenteService reagenteService;

    @PostMapping("/novo")
    @ResponseStatus(HttpStatus.CREATED)
    public Reagente salvar(@RequestBody @Valid ReagenteDTO dto){

        Reagente reagente = new Reagente();
        reagente.setNome(dto.getNome());
        reagente.setDescricao(dto.getDescricao());
        reagente.setQtd(dto.getQtd());
        reagente.setIdFornecedor(dto.getIdFornecedor());
        reagente.setIdAdm(dto.getIdAdm());
        reagente.setCategoria(dto.getCategoria());
        return repository.save(reagente);
    }

    @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        repository.deleteById(id);
    }

    @GetMapping("{id}")
    public Reagente getReagenteById(@PathVariable Integer id){
        Reagente reagente = reagenteService.getReagenteDetalhes(id);
        List<Category> category = categoryService.getCategory();
        Link selfLink = null;
        for (Category categorys : category) {
            Integer idCat = categorys.getId();
            selfLink = linkTo(CategoryControler.class).slash(idCat).withRel("Ir para categoria: " + categorys.getNome());
            categorys.add(selfLink);
        }
        Link link = linkTo(ReagenteControler.class).withRel("Lista de reagentes");
        reagente.add(selfLink, link);
        return reagente;
    }

    @GetMapping
    public CollectionModel<Reagente> getAllReagentes(){
        List<Reagente> reagentes = reagenteService.getReagentes();
        for (Reagente reagente : reagentes){
            Integer id = reagente.getId();
            Link selfLink = linkTo(ReagenteControler.class).withRel("Detalhes do reagente");
            reagente.add(selfLink);
        }
        Link link = linkTo(ReagenteControler.class).withRel("Lista de reagentes");
        CollectionModel<Reagente> retorno = CollectionModel.of(reagentes, link);
        return retorno;
    }

    @PutMapping("{id}/atualizar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Reagente atualReagente){
        repository
                .findById(id)
                .map(reagente -> {
                    atualReagente.setId(reagente.getId());
                    return repository.save(atualReagente);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reagente Inválido"));
    }
}
