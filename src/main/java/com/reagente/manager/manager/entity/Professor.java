package com.reagente.manager.manager.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Professor extends RepresentationModel<Professor> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 60)
    private String nome;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 20, unique = true)
    private String matricula;

    @Column(nullable = false, length = 20)
    private String senha;

    @OneToMany(mappedBy = "id")
    private List<Projeto> projetos = new ArrayList<>();
}
