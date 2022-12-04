package com.reagente.manager.manager.controler.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ReagenteDTO {

    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @NotEmpty(message = "{campo.desc.obrigatorio}")
    private String descricao;

    @NotEmpty(message = "{campo.qtd.obrigatorio}")
    private String qtd;

    private Integer idFornecedor;

    @NotNull(message = "{campo.adm.obrigatorio}")
    private Integer IdAdm;

    @NotNull(message = "{campo.cat.obrigatorio}")
    private Integer categoria;

}
