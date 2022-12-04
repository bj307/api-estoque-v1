package com.reagente.manager.manager.controler;

import com.reagente.manager.manager.controler.dto.UsuarioDTO;
import com.reagente.manager.manager.entity.Administrador;
import com.reagente.manager.manager.entity.Professor;
import com.reagente.manager.manager.repository.AdministradorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/adm")
@RequiredArgsConstructor
public class AdministradorControler {

    private final AdministradorRepository repository;

    @PostMapping("/novo")
    @ResponseStatus(HttpStatus.CREATED)
    public Administrador salvar(@RequestBody @Valid UsuarioDTO dto){

        Administrador administrador = new Administrador();
        administrador.setNome(dto.getNome());
        administrador.setMatricula(dto.getMatricula());
        administrador.setEmail(dto.getEmail());
        administrador.setSenha(dto.getSenha());
        return repository.save(administrador);
    }

    @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        repository.deleteById(id);
    }

}
