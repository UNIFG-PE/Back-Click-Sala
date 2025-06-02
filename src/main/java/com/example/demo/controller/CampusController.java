package com.example.demo.controller;

import com.example.demo.dto.CampusResponseDTO;
import com.example.demo.services.CampusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
