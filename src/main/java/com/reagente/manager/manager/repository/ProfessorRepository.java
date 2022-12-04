package com.reagente.manager.manager.repository;

import com.reagente.manager.manager.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

    Optional<Professor> findByNome(String nome);

    Optional<Professor> findByMatricula(String matProfessor);
}
