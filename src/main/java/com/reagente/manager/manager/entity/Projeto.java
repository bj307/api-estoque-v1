package com.reagente.manager.manager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Projeto extends RepresentationModel<Projeto> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 500)
    private String descricao;

    @Column(nullable = false, updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataTermino;

    private String matProfessor;

    private Integer idLaboratorio;

    @OneToMany(mappedBy = "id")
    private List<Aluno> alunos = new ArrayList<>();

    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDate.now());
    }

}
