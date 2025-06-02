package com.example.demo.controller;

import com.example.demo.dto.CampusRequestDTO;
import com.example.demo.dto.CampusResponseDTO;
import com.example.demo.services.CampusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/campus")
@RequiredArgsConstructor
public class CampusController {

    private final CampusService campusService;

    @GetMapping
    public ResponseEntity<List<CampusResponseDTO>> getAll() {
        List<CampusResponseDTO> campus = campusService.findAll();
        return ResponseEntity.ok(campus);
    }

    @PostMapping
    public ResponseEntity<CampusResponseDTO> create(@RequestBody CampusRequestDTO dto) {
        CampusResponseDTO created = campusService.createCampus(dto);
        return ResponseEntity
                .created(URI.create("/campus/" + created.id()))
                .body(created);
    }
}
