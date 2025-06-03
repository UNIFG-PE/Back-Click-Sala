package com.example.demo.dto;

import com.example.demo.entities.Category;

public record CategoryResponseDTO(
        Long id,
        String name,
        String description
) {
    public CategoryResponseDTO(Category category) {
        this(
                category.getId(),
                category.getName(),
                category.getDescription());
    }
}
