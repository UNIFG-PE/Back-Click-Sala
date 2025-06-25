package com.example.demo.dto;

import com.example.demo.entities.Enum.BookingStatus;
import java.time.LocalDateTime;

public record RoomBookingResponseDTO(
        Long id,
        String title,
        String reason,
        LocalDateTime checkIn,
        LocalDateTime checkOut,
        BookingStatus status
) {}
