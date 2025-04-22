package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class SupportTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String reason;

    @ManyToOne
    @JoinColumn(name = "attendent_id")
    private User attendentId;
    @ManyToOne
    @JoinColumn(name = "room_booking_id")
    private RoomBooking roomBooking;
}
