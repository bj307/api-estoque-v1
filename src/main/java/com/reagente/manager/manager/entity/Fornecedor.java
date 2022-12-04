package com.reagente.manager.manager.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Fornecedor extends RepresentationModel<Fornecedor> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 60)
    private String nome;

    @CNPJ
    @Column(nullable = false, unique = true)
    private String CNPJ;

    @CPF
    @Column(nullable = false, unique = true)
    private String CPF;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 11)
    private String telefone;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false, length = 250)
    private String reagente;

    @OneToMany(mappedBy = "id")
    private List<Reagente> reagentes = new ArrayList<>();
}

