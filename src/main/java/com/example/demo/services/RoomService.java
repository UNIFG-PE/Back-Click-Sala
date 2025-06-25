package com.example.demo.services;

import com.example.demo.dto.RoomRequestDTO;
import com.example.demo.dto.RoomResponseDTO;
import com.example.demo.entities.Campus;
import com.example.demo.entities.Category;
import com.example.demo.entities.Enum.RoomStatus;
import com.example.demo.entities.Photo;
import com.example.demo.entities.Room;
import com.example.demo.repository.CampusRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final CampusRepository campusRepository;
    private final CategoryRepository categoryRepository;

    public List<RoomResponseDTO> findAll() {
        return roomRepository.findAll().stream()
                .map(RoomResponseDTO::new)
                .toList();
    }

    public RoomResponseDTO getRoomById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));

        return new RoomResponseDTO(room);
    }

    @Transactional
    public RoomResponseDTO createRoom(RoomRequestDTO dto) {
        if (roomRepository.existsByIdentifier(dto.identifier())) {
            throw new DataIntegrityViolationException("Room with this identifier already exists");
        }

        Campus campus = campusRepository.findById(dto.campusId())
                .orElseThrow(() -> new EntityNotFoundException("Campus not found"));

        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        Room room = new Room();
        room.setIdentifier(dto.identifier());
        room.setFloor(dto.floor());
        room.setCapacity(dto.capacity());
        room.setDescription(dto.description());
        room.setStatus(RoomStatus.valueOf(dto.status()));
        room.setCampus(campus);
        room.setCategory(category);
        String imageUrl = dto.imageUrl();
        if (imageUrl == null || imageUrl.isBlank()) {
            imageUrl = "https://images.unsplash.com/photo-1504384308090-c894fdcc538d?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80";
        }
        room.setImageUrl(imageUrl);


        Room saved = roomRepository.save(room);
        return new RoomResponseDTO(saved);
    }

    @Transactional
    public RoomResponseDTO updateRoom(Long id, RoomRequestDTO dto) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));

        room.setIdentifier(dto.identifier());
        room.setFloor(dto.floor());
        room.setCapacity(dto.capacity());
        room.setDescription(dto.description());
        room.setStatus(RoomStatus.valueOf(dto.status().toUpperCase()));

        Campus campus = campusRepository.findById(dto.campusId())
                .orElseThrow(() -> new EntityNotFoundException("Campus not found"));
        room.setCampus(campus);

        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        room.setCategory(category);

        String imageUrl = dto.imageUrl();
        if (imageUrl != null && !imageUrl.isBlank()) {
            room.setImageUrl(imageUrl);
        }

        Room updated = roomRepository.save(room);
        return new RoomResponseDTO(updated);
    }

    @Transactional
    public void deleteRoom(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));

        roomRepository.delete(room);
    }
}
