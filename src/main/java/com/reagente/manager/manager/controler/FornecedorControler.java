package com.reagente.manager.manager.controler;

import com.reagente.manager.manager.controler.dto.FornecedorDTO;
import com.reagente.manager.manager.entity.Fornecedor;
import com.reagente.manager.manager.repository.FornecedorRepository;
import com.reagente.manager.manager.service.FornecedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/fornecedor")
@RequiredArgsConstructor
public class FornecedorControler {

    private final FornecedorRepository repository;

    @Autowired
    FornecedorService fornecedorService;

    @PostMapping("/novo")
    @ResponseStatus(HttpStatus.CREATED)
    public Fornecedor salvar(@RequestBody @Valid FornecedorDTO dto){

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(dto.getNome());
        fornecedor.setCPF(dto.getCPF());
        fornecedor.setCNPJ(dto.getCNPJ());
        fornecedor.setReagente(dto.getReagente());
        fornecedor.setTelefone(dto.getTelefone());
        fornecedor.setEmail(dto.getEmail());
        fornecedor.setEndereco(dto.getEndereco());
        return repository.save(fornecedor);
    }

    @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        repository.deleteById(id);
    }

    @GetMapping("{id}")
    public Fornecedor getFornecedorById(@PathVariable Integer id){
        Fornecedor fornecedor = fornecedorService.getFornecedorDetalhes(id);
        Link link = linkTo(FornecedorControler.class).withSelfRel();
        fornecedor.add(link);
        return fornecedor;
    }

    @GetMapping
    public CollectionModel<Fornecedor> getAllFornecedores(){
        List<Fornecedor> fornecedores = fornecedorService.getFornecedores();
        for (Fornecedor fornecedor : fornecedores) {
            Integer id = fornecedor.getId();
            Link selfLink = linkTo(FornecedorControler.class).slash(id).withSelfRel();
            fornecedor.add(selfLink);
        }
        Link link = linkTo(FornecedorControler.class).withSelfRel();
        CollectionModel<Fornecedor> retorno = CollectionModel.of(fornecedores, link);

        return retorno;
    }

    @PutMapping("{id}/atualizar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Fornecedor atualFornecedor){
        repository
                .findById(id)
                .map(fornecedor -> {
                    atualFornecedor.setId(fornecedor.getId());
                    return repository.save(atualFornecedor);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor Inválido"));
    }
}
