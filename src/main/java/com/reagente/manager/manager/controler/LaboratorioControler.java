package com.reagente.manager.manager.controler;


import com.reagente.manager.manager.entity.Category;
import com.reagente.manager.manager.entity.Laboratorio;
import com.reagente.manager.manager.repository.LaboratorioRepository;
import com.reagente.manager.manager.service.LaboratorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/laboratorio")
@RequiredArgsConstructor
public class LaboratorioControler {

    private final LaboratorioService laboratorioService;
    private final LaboratorioRepository laboratorioRepository;

    @PostMapping("/novo")
    @ResponseStatus(HttpStatus.CREATED)
    public Laboratorio salvar(@RequestBody @Valid Laboratorio laboratorio){

        laboratorio.setNome(laboratorio.getNome());

        return laboratorioRepository.save(laboratorio);
    }

    @GetMapping("{id}")
    public Laboratorio getLagoratorioId(@PathVariable Integer id){
        Laboratorio laboratorio = laboratorioService.getLaboratorioDetalhes(id);
        Link link = linkTo(LaboratorioControler.class).withRel("Lista de laboratorios");
        laboratorio.add(link);
        return laboratorio;
    }

    @GetMapping
    public CollectionModel<Laboratorio> getAllLaboratorio(){
        List<Laboratorio> laboratorios = laboratorioService.getLaboratorio();
        for (Laboratorio laboratorio : laboratorios){
            Integer id = laboratorio.getId();
            Link selfLink = linkTo(LaboratorioControler.class).slash(id)
                    .withRel("Detalhes do Laboratório");
            laboratorio.add(selfLink);
        }
        Link link = linkTo(LaboratorioControler.class).withRel("Lista de Laboratorios");
        CollectionModel<Laboratorio> retorno = CollectionModel.of(laboratorios, link);
        return retorno;
    }

}
