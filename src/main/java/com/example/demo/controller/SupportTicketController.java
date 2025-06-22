package com.example.demo.controller;

import com.example.demo.dto.SupportTicketRequestDTO;
import com.example.demo.dto.SupportTicketResponseDTO;
import com.example.demo.services.SupportTicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/supporttickets")
@RequiredArgsConstructor
public class SupportTicketController {

    private final SupportTicketService supportTicketService;

    @GetMapping("/status")
    public String status (){
        return "SupportTicket controller running";
    }

    @GetMapping
    public ResponseEntity<List<SupportTicketResponseDTO>> getAllSupportTickets() {
        List<SupportTicketResponseDTO> tickets = supportTicketService.findAll();
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupportTicketResponseDTO> getSupportTicketById(@PathVariable Long id) {
        SupportTicketResponseDTO ticket = supportTicketService.findById(id);
        return ResponseEntity.ok(ticket);
    }

    @PostMapping
    public ResponseEntity<SupportTicketResponseDTO> createSupportTicket(@RequestBody @Valid SupportTicketRequestDTO requestDTO) {
        SupportTicketResponseDTO createdTicket = supportTicketService.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTicket);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupportTicketResponseDTO> updateSupportTicket(
            @PathVariable Long id,
            @RequestBody @Valid SupportTicketRequestDTO requestDTO) {
        SupportTicketResponseDTO updatedTicket = supportTicketService.update(id, requestDTO);
        return ResponseEntity.ok(updatedTicket);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupportTicket(@PathVariable Long id) {
        supportTicketService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
