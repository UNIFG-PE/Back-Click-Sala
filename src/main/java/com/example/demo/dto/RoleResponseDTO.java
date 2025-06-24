package com.example.demo.dto;

import java.util.Set;

public record RoleResponseDTO(
    Long id,
    String name,
    Set<PermissionResponseDTO> permissions
) {
}
