package com.example.demo.dto;

public record RoomRequestDTO(
        String identifier,
        Integer floor,
        Integer capacity,
        String description,
        String status,
        Long campusId,
        Long categoryId
) {
}
