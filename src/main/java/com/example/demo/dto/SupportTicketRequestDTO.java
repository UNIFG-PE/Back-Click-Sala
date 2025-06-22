package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupportTicketRequestDTO {

    @NotBlank(message = "Reason is mandatory")
    private String reason;

    private Long attendentId;

    private Long roomBookingId;
}
