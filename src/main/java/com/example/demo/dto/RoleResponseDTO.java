package com.example.demo.dto;

import java.util.Set;

public record RoleResponseDTO(
    Long id,
    String name,
    String description,
    Set<PermissionResponseDTO> permissions
) {
}
