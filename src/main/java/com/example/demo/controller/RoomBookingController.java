package com.example.demo.controller;

import com.example.demo.dto.RoomBookingRequestDTO;
import com.example.demo.dto.RoomBookingResponseDTO;
import com.example.demo.services.RoomBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/roombookings")
@RequiredArgsConstructor
public class RoomBookingController {

    private final RoomBookingService roomBookingService;

    @GetMapping("/status")
    public String status() {
        return "RoomBooking controller running";
    }

    @PostMapping
    public ResponseEntity<RoomBookingResponseDTO> createBooking(@RequestBody RoomBookingRequestDTO dto) {
        RoomBookingResponseDTO response = roomBookingService.createBooking(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
