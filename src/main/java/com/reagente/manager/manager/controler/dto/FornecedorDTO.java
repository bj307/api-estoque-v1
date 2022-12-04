package com.reagente.manager.manager.controler.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class FornecedorDTO {

    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @NotEmpty(message = "{campo.cpf.obrigatorio}")
    private String CPF;

    @NotEmpty(message = "{campo.cnpj.obrigatorio}")
    private String CNPJ;

    @NotEmpty(message = "{campo.reagente.obrigatorio}")
    private String reagente;

    @NotEmpty(message = "{campo.endereco.obrigatorio}")
    private String endereco;

    @NotEmpty(message = "{campo.telefone.obrigatorio}")
    private String telefone;

    @NotEmpty(message = "{campo.email.obrigatorio}")
    private String email;
}
