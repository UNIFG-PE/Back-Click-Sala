package com.example.demo.dto;

import java.util.Set;

public record RoleRequestDTO(
    String name,
    Set<Long> permissionIds
) {
}
