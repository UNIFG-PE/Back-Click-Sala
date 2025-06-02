package com.example.demo.services;

import com.example.demo.dto.CampusRequestDTO;
import com.example.demo.entities.Campus;
import com.example.demo.repository.CampusRepository;
import com.example.demo.dto.CampusResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampusService {

    private final CampusRepository campusRepository;

    public List<CampusResponseDTO> findAll() {
        return campusRepository.findAll().stream()
                .map(CampusResponseDTO::new)
                .toList();
    }

    public CampusResponseDTO createCampus(CampusRequestDTO dto) {
        Campus campus = new Campus();
        campus.setName(dto.name());
        campus.setAddress(dto.address());

        Campus saved = campusRepository.save(campus);
        return new CampusResponseDTO(saved);
    }

    public CampusResponseDTO updateCampus(Long id, CampusRequestDTO dto) {
        Campus campus = campusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Campus not found"));

        campus.setName(dto.name());
        campus.setAddress(dto.address());

        Campus updatedCampus = campusRepository.save(campus);

        return new CampusResponseDTO(updatedCampus);
    }

    public void deleteCampus(Long id) {
        Campus campus = campusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Campus not found"));

        campusRepository.delete(campus);
    }
}
