package com.reagente.manager.manager.controler;


import com.reagente.manager.manager.controler.dto.ProjetoDTO;
import com.reagente.manager.manager.entity.Professor;
import com.reagente.manager.manager.entity.Projeto;
import com.reagente.manager.manager.repository.ProfessorRepository;
import com.reagente.manager.manager.repository.ProjetoRepository;
import com.reagente.manager.manager.service.ProfessorService;
import com.reagente.manager.manager.service.ProjetoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projeto")
public class ProjetoControler {

    private final ProjetoRepository repository;
    private final ProfessorRepository professorRepository;
    private final ProfessorService professorService;

    @Autowired
    ProjetoService projetoService;

    @PostMapping("/novo")
    @ResponseStatus(HttpStatus.CREATED)
    public Projeto salvar(@RequestBody @Valid ProjetoDTO dto){
        LocalDate dataTermino = LocalDate.parse(dto.getDataTermino(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String matProfessor = dto.getMatProfessor();
        Professor professor = professorRepository.findByMatricula(matProfessor)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Professor inexistente"));
        Projeto projeto = new Projeto();
        projeto.setNome(dto.getNome());
        projeto.setDescricao(dto.getDescricao());
        projeto.setDataTermino(dataTermino);
        projeto.setMatProfessor(professor.getMatricula());
        projeto.setIdLaboratorio(dto.getIdLaboratorio());
        return repository.save(projeto);
    }


    @GetMapping("{id}")
    public Projeto getProjetoById(@PathVariable Integer id) {
        Projeto projeto = projetoService.getProjetoDetalhes(id);
        List<Professor> professor = professorService.getprofessor();
        Link selfLink = null;
        for (Professor professores : professor) {
            Integer idProf = professores.getId();
            selfLink = linkTo(ProfessorControler.class).slash(idProf).withRel("Detalhes do professor");
            professores.add(selfLink);
        }
        Link link = linkTo(ProjetoControler.class).withRel("Lista de projetos");
        projeto.add(selfLink, link);
        return (projeto);
    }


    @GetMapping
    public CollectionModel<Projeto> getAllProjetos(){
        List<Projeto> projetos = projetoService.getProjetos();
        for (Projeto projeto : projetos) {
            Integer id = projeto.getId();
            Link selfLink = linkTo(ProjetoControler.class).slash(id)
                    .withRel("Detalhes do projeto");
            projeto.add(selfLink);
        }

        CollectionModel<Projeto> retorno = CollectionModel.of(projetos);
        return retorno;
    }

    @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        repository.deleteById(id);
    }

    @PutMapping("{id}/atualizar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Projeto atualProjeto){
        repository
                .findById(id)
                .map(projeto -> {
                    atualProjeto.setId(projeto.getId());
                    return repository.save(atualProjeto);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projeto Inválido"));
    }
}
