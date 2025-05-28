package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.repository.AlterarSenhaDTO; // Importa o DTO que contém os dados da requisiçã


import com.example.demo.services.UserService;

@RestController // Define que essa classe é um controlador REST
@RequestMapping("/usuarios") // Todas as rotas aqui começarão com /usuarios
public class ResetPasswordController {
    

    @Autowired // Injeta o serviço com a lógica de negócio
    private ResetPasswordService resetPasswordService;
    // private UserService userService;

    @PostMapping("/alterar-senha") // Define o endpoint POST para /usuarios/alterar-senha
    public ResponseEntity<String> alterarSenha(@RequestBody AlterarSenhaDTO dto) {
        // Chama o serviço passando os dados do DTO
        String resultado = userService.alterarSenha(dto.getEmail(), dto.getNovaSenha(), dto.getConfirmarSenha());

        // Retorna a resposta para o cliente (front)
        return ResponseEntity.ok(resultado);
    }
}
