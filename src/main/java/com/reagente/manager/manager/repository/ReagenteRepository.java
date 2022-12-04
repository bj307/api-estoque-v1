package com.reagente.manager.manager.repository;

import com.reagente.manager.manager.entity.Professor;
import com.reagente.manager.manager.entity.Reagente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReagenteRepository  extends JpaRepository<Reagente, Integer> {


}
