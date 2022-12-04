package com.reagente.manager.manager.controler.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;


@Data
@NoArgsConstructor
public class ErrosDTO {

    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @NotEmpty(message = "{campo.desc.obrigatorio}")
    private String descricao;

    @NotEmpty(message = "{campo.datTer.obrigatorio}")
    private String dataTermino;

    @NotEmpty(message = "{campo.matricula.obrigatorio}")
    private String matricula;

    @NotEmpty(message = "{campo.reagente.obrigatorio}")
    private String reagente;

    @NotEmpty(message = "{campo.endereco.obrigatorio}")
    private String endereco;

    @NotEmpty(message = "{campo.telefone.obrigatorio}")
    private String telefone;

    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String senha;

    @NotEmpty(message = "{campo.email.obrigatorio}")
    private String email;

}
