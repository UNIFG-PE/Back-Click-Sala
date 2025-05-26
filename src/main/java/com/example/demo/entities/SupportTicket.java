package com.example.demo.entities;

import com.example.demo.audit.AuditModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;

@Entity
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class SupportTicket extends AuditModel {

    @Column(nullable = false)
    private String reason;

    @ManyToOne
    @JoinColumn(name = "attendent_id")
    private User attendentId;
    @ManyToOne
    @JoinColumn(name = "room_booking_id")
    private RoomBooking roomBooking;
    @OneToMany(mappedBy = "supportTicket")
    private HashSet<Photo> photos = new HashSet<>();
}
