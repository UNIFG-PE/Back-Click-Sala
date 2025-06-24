package com.example.demo.entities;

import com.example.demo.audit.AuditModel;
import com.example.demo.entities.Enum.BookingStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;

@Entity
@Getter
@Setter
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
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    // Relacionamento com o usuário que fez a reserva, permite listar reservas por usuário
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "roomBooking")
    private HashSet<SupportTicket> supportTickets = new HashSet<>();

    //  Método para calcular duração da reserva já formatada (por exemplo: "02h 30m")
    @Transient
    public String getDuracaoFormatada() {
        long minutos = Duration.between(checkIn, checkOut).toMinutes();
        long horas = minutos / 60;
        long restoMinutos = minutos % 60;
        return String.format("%02dh %02dm", horas, restoMinutos);
    }

    // Validação da reserva (check-out deve ser após o check-in)
    @AssertTrue(message = "Check-out deve ser posterior ao check-in")
    @Transient
    public boolean isPeriodoValido() {
        return checkOut != null && checkIn != null && checkOut.isAfter(checkIn);
    }
}
