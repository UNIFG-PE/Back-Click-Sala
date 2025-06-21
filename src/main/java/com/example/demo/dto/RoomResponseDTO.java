package com.example.demo.dto;

import com.example.demo.entities.Room;

public record RoomResponseDTO(
        Long id,
        String identifier,
        Integer floor,
        Integer capacity,
        String description,
        String status,
        String campusName,
        String categoryName
) {
    public RoomResponseDTO(Room room) {
        this(
                room.getId(),
                room.getIdentifier(),
                room.getFloor(),
                room.getCapacity(),
                room.getDescription(),
                room.getStatus().name(),
                room.getCampus() != null ? room.getCampus().getName() : null,
                room.getCategory() != null ? room.getCategory().getName() : null
        );
    }
}
