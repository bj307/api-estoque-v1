package com.reagente.manager.manager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reagente extends RepresentationModel<Reagente> {

    @Column(nullable = false, length = 60)
    private String nome;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 500)
    private String descricao;

    @Column(nullable = false, length = 5)
    private String qtd;

    @Column(nullable = false, updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @NotNull
    private Integer idFornecedor;

    @NotNull
    private Integer idAdm;

    private Integer categoria;

    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDate.now());
    }

}
