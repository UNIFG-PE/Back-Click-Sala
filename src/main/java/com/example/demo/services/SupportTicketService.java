package com.example.demo.services;

import com.example.demo.dto.SupportTicketRequestDTO;
import com.example.demo.dto.SupportTicketResponseDTO;
import com.example.demo.dto.mapper.SupportTicketMapper;
import com.example.demo.entities.RoomBooking;
import com.example.demo.entities.SupportTicket;
import com.example.demo.entities.User;
import com.example.demo.repository.RoomBookingRepository;
import com.example.demo.repository.SupportTicketRepository;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupportTicketService {

    private final SupportTicketRepository supportTicketRepository;
    private final SupportTicketMapper supportTicketMapper;
    private final UserRepository userRepository;
    private final RoomBookingRepository roomBookingRepository;

    public List<SupportTicketResponseDTO> findAll() {
        return supportTicketRepository.findAll()
                .stream()
                .map(supportTicketMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public SupportTicketResponseDTO findById(Long id) {
        SupportTicket supportTicket = supportTicketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Support ticket not found with id: " + id));
        return supportTicketMapper.toResponseDTO(supportTicket);
    }

    @Transactional
    public SupportTicketResponseDTO create(SupportTicketRequestDTO requestDTO) {
        SupportTicket supportTicket = supportTicketMapper.toEntity(requestDTO);

        if (requestDTO.getRoomBookingId() != null) {
            RoomBooking roomBooking = roomBookingRepository.findById(requestDTO.getRoomBookingId())
                    .orElseThrow(() -> new EntityNotFoundException("Room booking not found with id: " + requestDTO.getRoomBookingId()));
            supportTicket.setRoomBooking(roomBooking);
        }

        if (requestDTO.getAttendentId() != null) {
            User attendent = userRepository.findById(requestDTO.getAttendentId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + requestDTO.getAttendentId()));
            supportTicket.setAttendentId(attendent);
        }

        SupportTicket savedTicket = supportTicketRepository.save(supportTicket);
        return supportTicketMapper.toResponseDTO(savedTicket);
    }

    @Transactional
    public SupportTicketResponseDTO update(Long id, SupportTicketRequestDTO requestDTO) {
        SupportTicket existingTicket = supportTicketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Support ticket not found with id: " + id));

        existingTicket.setReason(requestDTO.getReason());

        if (requestDTO.getRoomBookingId() != null) {
            RoomBooking roomBooking = roomBookingRepository.findById(requestDTO.getRoomBookingId())
                    .orElseThrow(() -> new EntityNotFoundException("Room booking not found with id: " + requestDTO.getRoomBookingId()));
            existingTicket.setRoomBooking(roomBooking);
        } else {
            existingTicket.setRoomBooking(null);
        }

        if (requestDTO.getAttendentId() != null) {
            User attendent = userRepository.findById(requestDTO.getAttendentId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + requestDTO.getAttendentId()));
            existingTicket.setAttendentId(attendent);
        } else {
            existingTicket.setAttendentId(null);
        }

        SupportTicket updatedTicket = supportTicketRepository.save(existingTicket);
        return supportTicketMapper.toResponseDTO(updatedTicket);
    }

    @Transactional
    public void delete(Long id) {
        if (!supportTicketRepository.existsById(id)) {
            throw new EntityNotFoundException("Support ticket not found with id: " + id);
        }
        supportTicketRepository.deleteById(id);
    }

    @Transactional
    public SupportTicketResponseDTO assignAttendent(Long ticketId, Long attendentId) {
        SupportTicket ticket = supportTicketRepository.findById(ticketId)
                .orElseThrow(() -> new EntityNotFoundException("SupportTicket não encontrado com ID: " + ticketId));

        User attendent = userRepository.findById(attendentId)
                .orElseThrow(() -> new EntityNotFoundException("User não encontrado com ID: " + attendentId));

        ticket.setAttendentId(attendent);
        ticket.setLastModifiedAt(Instant.now());

        SupportTicket savedTicket = supportTicketRepository.save(ticket);

        return supportTicketMapper.toResponseDTO(savedTicket);
    }
}
