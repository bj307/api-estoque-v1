package com.reagente.manager.manager.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Aluno extends RepresentationModel<Aluno> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 60)
    private String nome;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 20, unique = true)
    private String matricula;

    private Integer idProjeto;

    @Column(nullable = false, length = 20)
    private String senha;

}
