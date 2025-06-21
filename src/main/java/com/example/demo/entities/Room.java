package com.example.demo.entities;

import com.example.demo.audit.AuditModel;
import com.example.demo.entities.Enum.RoomStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room extends AuditModel {

    @Column(nullable = false, unique = true)
    private String identifier;
    @Column(nullable = false)
    private Integer floor;
    @Column(nullable = false)
    private Integer capacity;
    @Column(nullable = false)
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomStatus status;

    @ManyToOne
    @JoinColumn(name = "campus_id", nullable = false)
    private Campus campus;
    @ManyToOne
    @JoinColumn(name= "category_id", nullable = false)
    private Category category;
    @OneToMany(mappedBy = "room", cascade = CascadeType.REMOVE)
    private Set<RoomFeature> roomFeatures = new HashSet<>();
    @OneToMany(mappedBy = "room")
    private Set<RoomBooking> roomBookings = new HashSet<>();
    @OneToMany(mappedBy = "room")
    private Set<Photo> photos = new HashSet<>();
}
