package com.example.demo.services;

import com.example.demo.dto.CampusRequestDTO;
import com.example.demo.dto.CampusResponseDTO;
import com.example.demo.dto.RoomFeatureRequestDTO;
import com.example.demo.dto.RoomFeatureResponseDTO;
import com.example.demo.entities.Campus;
import com.example.demo.entities.RoomFeature;
import com.example.demo.repository.RoomFeatureRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public RoomFeatureResponseDTO createFeature(RoomFeatureRequestDTO dto) {
        if (roomFeatureRepository.existsByName(dto.name())) {
            throw new DataIntegrityViolationException("Feature already exists");
        }

        RoomFeature feature = new RoomFeature();
        feature.setName(dto.name());
        feature.setQuantity(dto.quantity());

        RoomFeature saved = roomFeatureRepository.save(feature);
        return new RoomFeatureResponseDTO(saved);
    }
}
