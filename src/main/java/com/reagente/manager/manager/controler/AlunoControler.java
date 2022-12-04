package com.reagente.manager.manager.controler;

import com.reagente.manager.manager.controler.dto.UsuarioDTO;
import com.reagente.manager.manager.entity.Aluno;
import com.reagente.manager.manager.entity.Projeto;
import com.reagente.manager.manager.repository.AlunoRepository;
import com.reagente.manager.manager.repository.ProjetoRepository;
import com.reagente.manager.manager.service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/aluno")
@RequiredArgsConstructor
public class AlunoControler {

    private final AlunoRepository alunoRepository;
    private final ProjetoRepository projetoRepository;

    @Autowired
    AlunoService alunoService;

    @PostMapping("/novo")
    @ResponseStatus(HttpStatus.CREATED)
    public Aluno salvar(@RequestBody @Valid UsuarioDTO dto){


        Aluno aluno = new Aluno();
        aluno.setNome(dto.getNome());
        aluno.setMatricula(dto.getMatricula());
        aluno.setEmail(dto.getEmail());
        aluno.setSenha(dto.getSenha());
        aluno.setIdProjeto(dto.getIdProjeto());
        return alunoRepository.save(aluno);
    }

    @GetMapping("{id}")
    public Aluno getAlunoById(@PathVariable Integer id){
        Aluno aluno = alunoService.getAlunoDetalhes(id);
        Link link = linkTo(ProjetoControler.class).slash(aluno.getIdProjeto()).withRel("Detalhes do projetos");
        aluno.add(link);
        return aluno;
    }

    @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        alunoRepository.deleteById(id);
    }

}
