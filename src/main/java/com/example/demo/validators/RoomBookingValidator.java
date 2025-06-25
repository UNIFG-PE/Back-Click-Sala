package com.example.demo.validators;

import com.example.demo.entities.RoomBooking;
import java.time.LocalDateTime;

public class RoomBookingValidator {

    public static void validate(RoomBooking booking) {
        if (booking.getTitle() == null || booking.getTitle().isBlank()) {
            throw new IllegalArgumentException("Título da reserva é obrigatório.");
        }

        if (booking.getReason() == null || booking.getReason().isBlank()) {
            throw new IllegalArgumentException("Motivo da reserva é obrigatório.");
        }

        if (booking.getCheckIn() == null || booking.getCheckOut() == null) {
            throw new IllegalArgumentException("Datas de check-in e check-out são obrigatórias.");
        }

        if (booking.getCheckIn().isAfter(booking.getCheckOut())) {
            throw new IllegalArgumentException("Data de check-in não pode ser posterior ao check-out.");
        }

        if (booking.getCheckIn().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Não é possível agendar uma sala no passado.");
        }
    }
}
