package com.Cadastro.cadastro.services;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Cadastro.cadastro.Dtos.UserDto;
import com.Cadastro.cadastro.models.User;
import com.Cadastro.cadastro.repositorys.UserRepository;


@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User salvaUser(UserDto userDTO) {
        if (userDTO != null && isValidEmail(userDTO.getEmail()) && userDTO.getSenha() != null && !userDTO.getSenha().isEmpty()) {
            // Criptografar a senha antes de salvar
            String senhaCriptografada = encoder.encode(userDTO.getSenha());
            User user = new User();
            user.setNome(userDTO.getNome());
            user.setEmail(userDTO.getEmail());
            user.setSenha(senhaCriptografada);
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Dados incompletos ou inválidos.");
        }
    }

    public List<User> listarUser(){
        return userRepository.findAll();
    }

    public Optional<User> buscarUserPorId(Long id) {
        return userRepository.findById(id);
    }

    public void removerUser(Long id) {
        userRepository.deleteById(id);
    }

    public User atualizarUser(Long id , UserDto userDTO) {
        Optional<User> userExistenteObj = userRepository.findById(id);

        if(userExistenteObj.isPresent()) {
            User userExistente = userExistenteObj.get();

            userExistente.setNome(userDTO.getNome());
            userExistente.setEmail(userDTO.getEmail());
            String senhaCriptografada = encoder.encode(userDTO.getSenha());
            userExistente.setSenha(senhaCriptografada);

            return userRepository.save(userExistente);
        } else {
            return null;
        }
    }

    public static boolean isValidEmail(String email) {
        // Expressão regular para verificar o formato do email
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public Optional<User> confirmarLogin(String email, String senha) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && encoder.matches(senha, user.get().getSenha())) {
            return user;
        } else {
            return Optional.empty();
        }
    }
}
