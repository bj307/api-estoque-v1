package com.reagente.manager.manager.repository;

import com.reagente.manager.manager.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    Optional<Aluno> findByNome(String nome);
}
