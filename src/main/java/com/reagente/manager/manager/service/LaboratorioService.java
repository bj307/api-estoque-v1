package com.reagente.manager.manager.service;

import com.reagente.manager.manager.entity.Laboratorio;
import com.reagente.manager.manager.repository.LaboratorioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaboratorioService {

    private final LaboratorioRepository laboratorioRepository;

    public LaboratorioService(LaboratorioRepository laboratorioRepository) {
        this.laboratorioRepository = laboratorioRepository;
    }

    public List<Laboratorio> getLaboratorio() {return laboratorioRepository.findAll();}

    public Laboratorio getLaboratorioDetalhes(Integer id){
        for (Laboratorio laboratorio : getLaboratorio()){
            if (laboratorio.getId().equals(id)){
                return laboratorio;
            }
        }
        return null;
    }
}
