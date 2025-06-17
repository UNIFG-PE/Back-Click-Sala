package com.example.demo.dto;

import com.example.demo.entities.Campus;

public record CampusResponseDTO(
        Long id,
        String name,
        String address
) {
    public CampusResponseDTO(Campus campus) {
        this(
                campus.getId(),
                campus.getName(),
                campus.getAddress());
    }
}
