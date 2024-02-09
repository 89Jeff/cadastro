package com.Cadastro.cadastro.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Cadastro.cadastro.models.Car;

public interface CarRepository extends JpaRepository<Car , Long> {
    
}
