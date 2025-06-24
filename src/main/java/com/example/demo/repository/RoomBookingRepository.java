package com.example.demo.repository;

import com.example.demo.entities.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomBookingRepository extends JpaRepository<RoomBooking, Long> {

    // Recupera as reservas feitas por um usuário
    List<RoomBooking> findByUserId(Long userId);
}
