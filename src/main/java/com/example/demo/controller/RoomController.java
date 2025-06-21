package com.example.demo.controller;

import com.example.demo.dto.RoomRequestDTO;
import com.example.demo.dto.RoomResponseDTO;
import com.example.demo.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/status")
    public String status (){
        return "Room controller running";
    }

    @GetMapping
    public ResponseEntity<List<RoomResponseDTO>> getAll() {
        List<RoomResponseDTO> room = roomService.findAll();
        return ResponseEntity.ok(room);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponseDTO> getById(@PathVariable Long id) {
        RoomResponseDTO room = roomService.getRoomById(id);
        return ResponseEntity.ok(room);
    }

    @PostMapping
    public ResponseEntity<RoomResponseDTO> create(@RequestBody RoomRequestDTO dto) {
        RoomResponseDTO created = roomService.createRoom(dto);
        return ResponseEntity
                .created(URI.create("/rooms/" + created.id()))
                .body(created);
    }
}
