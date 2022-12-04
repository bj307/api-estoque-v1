package com.reagente.manager.manager.service;

import com.reagente.manager.manager.entity.Administrador;
import com.reagente.manager.manager.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService implements UserDetailsService {

    @Autowired
    private AdministradorRepository repository;

    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
        Administrador administrador = repository.findByNome(nome).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return User.builder().username(administrador.getNome()).password(administrador.getSenha()).roles("ADM").build();
    }
}
