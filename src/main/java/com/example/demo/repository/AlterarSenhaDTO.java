package com.example.demo.repository;

public class AlterarSenhaDTO {

    private String email; // E-mail do usuário que quer trocar a senha
    private String novaSenha; // Nova senha a ser cadastrada
    private String confirmarSenha; // Confirmação da nova senha (para validação)

    // Getters e setters obrigatórios (ou use Lombok com @Getter @Setter se preferir)

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }
}
