package com.reagente.manager.manager.controler.dto;

import com.reagente.manager.manager.entity.Aluno;
import com.reagente.manager.manager.entity.Professor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@NoArgsConstructor
public class ProjetoDTO {

    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @NotEmpty(message = "{campo.desc.obrigatorio}")
    private String descricao;

    @NotEmpty(message = "{campo.datTer.obrigatorio}")
    private String dataTermino;

    @NotEmpty(message = "{campo.prof.obrigatorio}")
    private String matProfessor;

    @NotNull(message = "{campo.lab.obrigatorio}")
    private Integer idLaboratorio;

    private List<Aluno> alunos;

}
