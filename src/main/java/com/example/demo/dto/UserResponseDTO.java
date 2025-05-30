package com.example.demo.dto;

import com.example.demo.entities.Enum.UserStatus;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;

import java.util.Set;
import java.util.stream.Collectors;

public record UserResponseDTO(
        Long id,
        String fullName,
        String cpf,
        String phoneNumber,
        String email,
        UserStatus status,
        Set<String> roles,
        String photoUrl
) {
    public UserResponseDTO(User user) {
        this(
                user.getId(),
                user.getFullName(),
                user.getCpf(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getStatus(),
                user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()),
                user.getPhotoUrl() != null ? user.getPhotoUrl().getImageUrl() : null
        );
    }
}
