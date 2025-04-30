package com.example.demo.entities;

import com.example.demo.audit.AuditModel;
import com.example.demo.entities.Enum.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomBooking extends AuditModel {

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String reason;
    @Column(nullable = false)
    private LocalDateTime checkIn;
    @Column(nullable = false)
    private LocalDateTime checkOut;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    @OneToMany(mappedBy = "roomBooking")
    private HashSet<SupportTicket> supportTickets = new HashSet<>();
}
