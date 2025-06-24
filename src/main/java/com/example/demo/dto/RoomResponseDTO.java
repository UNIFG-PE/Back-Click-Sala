package com.example.demo.dto;

import com.example.demo.entities.Room;

public record RoomResponseDTO(
        Long id,
        String identifier,
        Integer floor,
        Integer capacity,
        String description,
        String status,
        Long campusId,
        String campusName,
        Long categoryId,
        String categoryName,
        String imageUrl
) {
    public RoomResponseDTO(Room room) {
        this(
                room.getId(),
                room.getIdentifier(),
                room.getFloor(),
                room.getCapacity(),
                room.getDescription(),
                room.getStatus().name(),
                room.getCampus() != null ? room.getCampus().getId() : null,
                room.getCampus() != null ? room.getCampus().getName() : null,
                room.getCategory() != null ? room.getCategory().getId() : null,
                room.getCategory() != null ? room.getCategory().getName() : null,
                room.getImageUrl()
        );
    }
}
