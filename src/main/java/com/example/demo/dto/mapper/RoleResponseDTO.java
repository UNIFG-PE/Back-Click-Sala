package com.example.demo.dto.mapper;

import java.util.Set;

public record RoleResponseDTO(
        Long id,
        String name,
        Set<com.example.demo.dto.PermissionResponseDTO> permissions
) {
}
