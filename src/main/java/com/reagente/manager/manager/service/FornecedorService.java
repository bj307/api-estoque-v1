package com.reagente.manager.manager.service;

import com.reagente.manager.manager.entity.Fornecedor;
import com.reagente.manager.manager.repository.FornecedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {

    private final FornecedorRepository repository;

    public FornecedorService(FornecedorRepository repository) {
        this.repository = repository;
    }
    public List<Fornecedor> getFornecedores(){
        return repository.findAll();
    }

    public Fornecedor getFornecedorDetalhes(Integer id){
        for (Fornecedor fornecedor : getFornecedores()){
            if (fornecedor.getId().equals(id)){
                return fornecedor;
            }
        }
        return null;
    }
}
