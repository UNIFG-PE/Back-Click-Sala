package com.example.demo.entities;

import com.example.demo.audit.AuditModel;
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
public class Permission extends AuditModel {

    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String description;

    private LocalDateTime dia;

    @ManyToMany(mappedBy = "permissions")
    private HashSet<Role> roles = new HashSet<>();

}
