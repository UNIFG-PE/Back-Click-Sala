package com.example.demo.dto;

import com.example.demo.entities.Enum.UserStatus;

import java.util.Set;

public record UserRequestDTO(
        String fullName,
        String cpf,
        String phoneNumber,
        String email,
        String password,
        UserStatus status,
        Set<Long> roleIds // você irá buscar as Roles pelo ID
) {}