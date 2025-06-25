package com.example.demo.services;

import com.example.demo.dto.RoomBookingRequestDTO;
import com.example.demo.dto.RoomBookingResponseDTO;
import com.example.demo.dto.mapper.RoomBookingMapper;
import com.example.demo.entities.Room;
import com.example.demo.entities.RoomBooking;
import com.example.demo.entities.Enum.RoomStatus;
import com.example.demo.repository.RoomBookingRepository;
import com.example.demo.repository.RoomRepository;
import org.springframework.stereotype.Service;

@Service
public class RoomBookingService {

    private final RoomBookingRepository roomBookingRepository;
    private final RoomRepository roomRepository;

    public RoomBookingService(RoomBookingRepository roomBookingRepository, RoomRepository roomRepository) {
        this.roomBookingRepository = roomBookingRepository;
        this.roomRepository = roomRepository;
    }

    public RoomBookingResponseDTO createBooking(RoomBookingRequestDTO dto) {
        Room room = roomRepository.findById(dto.roomId())
                .orElseThrow(() -> new RuntimeException("Sala não encontrada"));

        if (room.getStatus() != RoomStatus.AVAILABLE) {
            throw new IllegalArgumentException("Sala selecionada não está disponível.");
        }

        RoomBooking booking = RoomBooking.create(
                dto.title(),
                dto.reason(),
                dto.checkIn(),
                dto.checkOut(),
                room);

        RoomBooking savedBooking = roomBookingRepository.save(booking);

        return RoomBookingMapper.toResponseDTO(savedBooking);
    }

}
