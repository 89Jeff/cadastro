package com.Cadastro.cadastro.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome"  , length = 250 , nullable = false)
    private String nome;

    @Column(name = "email" , length = 250 , unique = true , nullable = false)
    private String email;

    @Column(name = "senha" ,  length = 300 , nullable = false)
    private String senha;
    
}
