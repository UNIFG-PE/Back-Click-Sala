package com.example.demo.repository;

public class changePasswordDto {

    private String email; // E-mail do usuário que quer trocar a senha
    private String newpassword; // Nova senha a ser cadastrada
    private String confirmpassword; // Confirmação da nova senha

    // Getters e setters obrigatórios (ou use Lombok com @Getter @Setter se preferir)

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.novaSenha = newpassword;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }
}
