package com.example.demo.entities;

import com.example.demo.entities.Enum.RoomStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String identifier;
    @Column(nullable = false)
    private String campus;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private Integer floor;
    @Column(nullable = false)
    private Integer capacity;
    @Column(nullable = false)
    private String descrition;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomStatus status;

    @OneToMany(mappedBy = "room")
    private HashSet<RoomFeature> roomFeatures = new HashSet<>();
}
