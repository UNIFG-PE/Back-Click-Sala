package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupportTicketResponseDTO {

    private Long id;
    private String reason;
    private Long attendentId;
    private Long roomBookingId;
    private Instant createdAt;
    private Instant lastModifiedAt;
}
