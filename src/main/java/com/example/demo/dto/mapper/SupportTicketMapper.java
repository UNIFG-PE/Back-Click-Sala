package com.example.demo.dto.mapper;

import com.example.demo.dto.SupportTicketRequestDTO;
import com.example.demo.dto.SupportTicketResponseDTO;
import com.example.demo.entities.SupportTicket;
import org.springframework.stereotype.Component;

@Component
public class SupportTicketMapper {

    public SupportTicket toEntity(SupportTicketRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        SupportTicket supportTicket = new SupportTicket();
        supportTicket.setReason(dto.getReason());
        // Other fields will be set in the service
        return supportTicket;
    }

    public SupportTicketResponseDTO toResponseDTO(SupportTicket supportTicket) {
        if (supportTicket == null) {
            return null;
        }

        SupportTicketResponseDTO dto = new SupportTicketResponseDTO();
        dto.setId(supportTicket.getId());
        dto.setReason(supportTicket.getReason());
        dto.setCreatedAt(supportTicket.getCreatedAt());
        dto.setLastModifiedAt(supportTicket.getLastModifiedAt());

        if (supportTicket.getAttendentId() != null) {
            dto.setAttendentId(supportTicket.getAttendentId().getId());
        } else {
            dto.setAttendentId(null);
        }

        if (supportTicket.getRoomBooking() != null) {
            dto.setRoomBookingId(supportTicket.getRoomBooking().getId());
        } else {
            dto.setRoomBookingId(null);
        }

        return dto;
    }
}
