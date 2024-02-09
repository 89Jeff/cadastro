package com.Cadastro.cadastro.Dtos;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CarDto {
    
    private String modelo;
    private String fabricante;
    private LocalDate anofabricacao;

    
}
