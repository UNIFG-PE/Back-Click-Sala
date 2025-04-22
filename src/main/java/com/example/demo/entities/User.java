package com.example.demo.entities;

import com.example.demo.entities.Enum.UserStatus;
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
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 150)
    private String fullName;
    @Column(nullable = false, length = 11)
    private String cpf;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false, unique = true, length = 150)
    private String email;
    @Column(nullable = false, length = 100)
    private String password;
    @Column(nullable = false)
    private String photoUrl;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus Status;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private HashSet<Role> roles = new HashSet<>();
}
