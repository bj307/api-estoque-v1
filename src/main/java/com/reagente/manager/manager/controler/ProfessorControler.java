package com.reagente.manager.manager.controler;


import com.reagente.manager.manager.controler.dto.UsuarioDTO;
import com.reagente.manager.manager.entity.Professor;
import com.reagente.manager.manager.entity.Projeto;
import com.reagente.manager.manager.repository.ProfessorRepository;
import com.reagente.manager.manager.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/professor")
@RequiredArgsConstructor
public class ProfessorControler {

    private final ProfessorRepository repository;
    private final ProfessorService professorService;

    @PostMapping("/novo")
    @ResponseStatus(HttpStatus.CREATED)
    public Professor salvar(@RequestBody @Valid UsuarioDTO dto){

        Professor professor = new Professor();
        professor.setNome(dto.getNome());
        professor.setMatricula(dto.getMatricula());
        professor.setEmail(dto.getEmail());
        professor.setSenha(dto.getSenha());
        return repository.save(professor);
    }

    @GetMapping("{id}")
    public Professor getProfessorById(@PathVariable Integer id){
        Professor professor = professorService.getProfessorDetalhes(id);
        Link link = linkTo(ProfessorControler.class).withRel("Lista de professor");
        professor.add(link);
        return professor;
    }

    @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        repository.deleteById(id);
    }
}
