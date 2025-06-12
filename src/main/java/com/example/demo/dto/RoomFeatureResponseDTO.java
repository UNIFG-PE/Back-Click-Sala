package com.example.demo.dto;

import com.example.demo.entities.RoomFeature;

public record RoomFeatureResponseDTO(
        Long id,
        String name,
        Integer quantity,
        Long roomId
) {
    public RoomFeatureResponseDTO(RoomFeature feature) {
        this(
                feature.getId(),
                feature.getName(),
                feature.getQuantity(),
                feature.getRoom() != null ? feature.getRoom().getId() : null
        );
    }
}
