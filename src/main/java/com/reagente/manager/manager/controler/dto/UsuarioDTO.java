package com.reagente.manager.manager.controler.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.reagente.manager.manager.entity.Projeto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
public class UsuarioDTO {

    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @NotEmpty(message = "{campo.matricula.obrigatorio}")
    private String matricula;

    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String senha;

    @NotEmpty(message = "{campo.email.obrigatorio}")
    private String email;

    private List<Projeto> projetos;

    private Integer idProjeto;
}
