package com.Cadastro.cadastro.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Cadastro.cadastro.models.User;

public interface UserRepository extends JpaRepository<User,Long>{
    
    Optional<User> findByEmail(String email);
}
