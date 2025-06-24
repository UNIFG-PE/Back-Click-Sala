package com.example.demo.dto.mapper;

import java.util.Set;

public record RoleResponseDTO(
    Long id,
    String name,
    Set<String> permissions
) {
}
