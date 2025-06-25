package com.example.demo.controller;

import com.example.demo.dto.RoomBookingDTO;
import com.example.demo.services.RoomBookingService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/roombookings")
@RequiredArgsConstructor
public class RoomBookingController {

    private final RoomBookingService roomBookingService;

    @GetMapping("/status")
    public String status (){
        return "RoomBooking controller running";
    }   
}
        @PostMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelarReserva(@PathVariable Long id, @RequestParam Long userId) {
        roomBookingService.cancelarReserva(id, userId);
        return ResponseEntity.noContent().build();
    }
