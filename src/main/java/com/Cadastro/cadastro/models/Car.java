package com.Cadastro.cadastro.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_carro")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "modelo" , nullable = false)
    private String modelo;

    @Column(name = "fabricante" , nullable = false)
    private String fabricante;

    @Column(name = "anofabricacao" , nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy") // Formata a data no formato desejado
    private LocalDate anofabricacao;
    
}
