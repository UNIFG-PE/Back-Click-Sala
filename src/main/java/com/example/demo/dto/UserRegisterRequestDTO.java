package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequestDTO {

    @NotBlank(message = "Full name is mandatory")
    private String fullName;

    @NotBlank(message = "Phone number is mandatory")
    private String cpf;

    @Pattern(
            regexp = "^\\+?\\d{1,3}?[-.\\s]?\\(?\\d{2,3}\\)?[-.\\s]?\\d{4,5}[-.\\s]?\\d{4}$",
            message = "Número de celular inválido"
    )
    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;

    @NotBlank
    @Size(min = 8)
    @Pattern(
            regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$",
            message = "Password must have at least 8 characters, including a letter, a number, and a special character"
    )
    private String password;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email address must be valid")
    private String email;
}

