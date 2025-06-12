package com.example.demo.controller;

import com.example.demo.dto.RoomFeatureResponseDTO;
import com.example.demo.services.RoomFeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/roomfeatures")
@RequiredArgsConstructor
public class RoomFeatureController {

    private final RoomFeatureService roomFeatureService;

    @GetMapping("/status")
    public String status (){
        return "RoomFeature controller running";
    }

    @GetMapping
    public ResponseEntity<List<RoomFeatureResponseDTO>> getAll() {
        List<RoomFeatureResponseDTO> feature = roomFeatureService.findAll();
        return ResponseEntity.ok(feature);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomFeatureResponseDTO> getById(@PathVariable Long id) {
        RoomFeatureResponseDTO feature = roomFeatureService.getFeatureById(id);
        return ResponseEntity.ok(feature);
    }
}
