package com.example.demo.entities;

import com.example.demo.audit.AuditModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends AuditModel {

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private HashSet<User> users = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private HashSet<Permission> permissions = new HashSet<>();
}
