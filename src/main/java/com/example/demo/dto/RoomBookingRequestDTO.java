package com.example.demo.dto;

import java.time.LocalDateTime;

public record RoomBookingRequestDTO(
    String title,
    String reason,
    LocalDateTime checkIn,
    LocalDateTime checkOut,
    Long roomId
) {}