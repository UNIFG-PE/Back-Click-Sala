package com.example.demo.dto;

import com.example.demo.entities.RoomBooking;

import java.time.LocalDateTime;

public class RoomBookingDTO {

    private Long id;
    private String title;
    private String reason;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private String duracao;
    private String status;
    private String roomName;
    private String campusName;
    private String pavimento;

    public RoomBookingDTO(RoomBooking r) {
        this.id = r.getId();
        this.title = r.getTitle();
        this.reason = r.getReason();
        this.checkIn = r.getCheckIn();
        this.checkOut = r.getCheckOut();
        this.duracao = r.getDuracaoFormatada();
        this.status = r.getStatus().name();
        this.roomName = r.getRoom().getIdentifier();
        this.campusName = r.getRoom().getCampus().getName();
        this.pavimento = r.getRoom().getFloor().toString();
    }

    
}
