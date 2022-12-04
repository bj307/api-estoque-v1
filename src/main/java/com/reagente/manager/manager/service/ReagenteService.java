package com.reagente.manager.manager.service;

import com.reagente.manager.manager.entity.Reagente;
import com.reagente.manager.manager.repository.ReagenteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReagenteService {

    public final ReagenteRepository repository;

    public List<Reagente> getReagentes() {
        return repository.findAll();
    }
    public Reagente getReagenteDetalhes(Integer id) {
        for (Reagente reagente : getReagentes()){
            if (reagente.getId().equals(id)){
                return reagente;
            }
        }
        return null;
    }
}
