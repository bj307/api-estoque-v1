package com.reagente.manager.manager.service;


import com.reagente.manager.manager.entity.Professor;
import com.reagente.manager.manager.entity.Projeto;
import com.reagente.manager.manager.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService implements UserDetailsService {

    @Autowired
    private ProfessorRepository repository;

    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
        Professor professor = repository.findByNome(nome).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return User.builder().username(professor.getNome()).password(professor.getSenha()).roles("PROF").build();
    }

    public Professor getProfessorDetalhes(Integer id) {
        for (Professor professor : getprofessor()){
            if (professor.getId().equals(id)){
                return professor;
            }
        }
        return null;
    }

    public List<Professor> getprofessor() {
        return repository.findAll();
    }
}
