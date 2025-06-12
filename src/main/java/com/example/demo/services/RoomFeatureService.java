package com.example.demo.services;

import com.example.demo.dto.RoomFeatureResponseDTO;
import com.example.demo.entities.RoomFeature;
import com.example.demo.repository.RoomFeatureRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomFeatureService {

    private final RoomFeatureRepository roomFeatureRepository;

    public List<RoomFeatureResponseDTO> findAll() {
        return roomFeatureRepository.findAll().stream()
                .map(RoomFeatureResponseDTO::new)
                .toList();
    }

    public RoomFeatureResponseDTO getFeatureById(Long id) {
        RoomFeature feature = roomFeatureRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Feature not found"));

        return new RoomFeatureResponseDTO(feature);
    }
}
