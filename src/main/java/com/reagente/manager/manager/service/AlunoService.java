package com.reagente.manager.manager.service;


import com.reagente.manager.manager.controler.AlunoControler;
import com.reagente.manager.manager.entity.Aluno;
import com.reagente.manager.manager.repository.AlunoRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AlunoService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
        Aluno aluno = repository.findByNome(nome).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return User.builder().username(aluno.getNome()).password(aluno.getSenha()).roles("USER").build();
    }


    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public List<Aluno> getAlunos() {return repository.findAll();}
    public Aluno getAlunoDetalhes(Integer id) {
        for (Aluno aluno: getAlunos()){
            if (aluno.getId().equals(id)){
                return aluno;
            }
        }
        return null;
    }


    public Aluno getAlunoById(Integer id) {
        return repository.findById(id).get();
    }
}
