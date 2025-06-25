package com.example.demo.dto.mapper;

import com.example.demo.dto.RoomBookingResponseDTO;
import com.example.demo.entities.RoomBooking;

public class RoomBookingMapper {
    public static RoomBookingResponseDTO toResponseDTO(RoomBooking booking) {
        return new RoomBookingResponseDTO(
                booking.getId(),
                booking.getTitle(),
                booking.getReason(),
                booking.getCheckIn(),
                booking.getCheckOut(),
                booking.getStatus());
    }
}