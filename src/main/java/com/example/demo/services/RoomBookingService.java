package com.example.demo.services;

import com.example.demo.dto.RoomBookingDTO;
import com.example.demo.entities.Enum.BookingStatus;
import com.example.demo.entities.RoomBooking;
import com.example.demo.repository.RoomBookingRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomBookingService {

    private final RoomBookingRepository roomBookingRepository;

    // Reservas de um usuário
    public List<RoomBookingDTO> listarReservasPorUsuario(Long userId) {
        return roomBookingRepository.findByUserId(userId).stream()
                .map(RoomBookingDTO::new)
                .collect(Collectors.toList());
    }
    // Detalhar uma reserva específica do usuário
    public RoomBookingDTO detalharReserva(Long id, Long userId) {
        RoomBooking reserva = roomBookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada"));

        if (!reserva.getUser().getId().equals(userId)) {
            throw new RuntimeException("Reserva não pertence ao usuário.");
        }

        return new RoomBookingDTO(reserva);
    }

    // Cancelar uma reserva
    public void cancelarReserva(Long bookingId, Long userId) {
        RoomBooking reserva = roomBookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada"));

        if (!reserva.getUser().getId().equals(userId)) {
            throw new RuntimeException("Reserva não pertence ao usuário.");
        }

        if (reserva.getStatus() == BookingStatus.APPROVED || reserva.getStatus() == BookingStatus.PENDING_APPROVAL) {
            reserva.setStatus(BookingStatus.REJECTED);
            roomBookingRepository.save(reserva);
        } else {
            throw new RuntimeException("Reserva não pode ser cancelada.");
        }
    }

}
