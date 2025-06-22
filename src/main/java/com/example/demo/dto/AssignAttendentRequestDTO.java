package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AssignAttendentRequestDTO {
    
    @NotNull(message = "Ticket ID é obrigatório")
    private Long ticketId;
    
    @NotNull(message = "Attendent ID é obrigatório")
    private Long attendentId;
}
