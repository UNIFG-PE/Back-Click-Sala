package com.example.demo.dto;

public record RoomFeatureRequestDTO(
        String name,
        Integer quantity,
        Long roomId
) {
}
