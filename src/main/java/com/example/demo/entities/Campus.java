package com.example.demo.entities;

import com.example.demo.audit.AuditModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Campus extends AuditModel {

    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "campus")
    private HashSet<Room> rooms = new HashSet<>();
}
