package com.example.demo.services;

import com.example.demo.dto.RoomFeatureRequestDTO;
import com.example.demo.dto.RoomFeatureResponseDTO;
import com.example.demo.entities.Room;
import com.example.demo.entities.RoomFeature;
import com.example.demo.repository.RoomFeatureRepository;
import com.example.demo.repository.RoomRepository;
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
    private final RoomRepository roomRepository;

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

        RoomFeature feature = new RoomFeature();
        feature.setName(dto.name());
        feature.setQuantity(dto.quantity());

        if (dto.roomId() != null) {
            Room room = roomRepository.findById(dto.roomId())
                    .orElseThrow(() -> new EntityNotFoundException("Room not found"));
            feature.setRoom(room);
        }

        RoomFeature saved = roomFeatureRepository.save(feature);
        return new RoomFeatureResponseDTO(saved);
    }

    @Transactional
    public RoomFeatureResponseDTO updateFeature(Long id, RoomFeatureRequestDTO dto) {
        RoomFeature feature = roomFeatureRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Feature not found"));

        feature.setName(dto.name());
        feature.setQuantity(dto.quantity());
        Room room = roomRepository.findById(dto.roomId())
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));
        feature.setRoom(room);

        RoomFeature updatedFeature = roomFeatureRepository.save(feature);

        return new RoomFeatureResponseDTO(updatedFeature);
    }

    @Transactional
    public void deleteFeature(Long id) {
        RoomFeature feature = roomFeatureRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Feature not found"));

        roomFeatureRepository.delete(feature);
    }
}
