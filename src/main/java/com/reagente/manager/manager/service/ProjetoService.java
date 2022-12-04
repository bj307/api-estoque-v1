package com.reagente.manager.manager.service;

import com.reagente.manager.manager.entity.Projeto;
import com.reagente.manager.manager.repository.ProfessorRepository;
import com.reagente.manager.manager.repository.ProjetoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetoService {

    private final ProjetoRepository repository;
    private final ProfessorRepository professorRepository;


    public ProjetoService(ProjetoRepository repository, ProfessorRepository professorRepository) {
        this.repository = repository;
        this.professorRepository = professorRepository;
    }

    public List<Projeto> getProjetos() {return repository.findAll();}

    public Projeto getProjetoDetalhes(Integer id){
        for (Projeto projeto : getProjetos()){
            if (projeto.getId().equals(id)){
                return projeto;
            }
        }
        return null;
    }



}
