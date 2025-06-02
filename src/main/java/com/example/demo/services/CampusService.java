package com.example.demo.services;

import com.example.demo.repository.CampusRepository;
import com.example.demo.dto.CampusResponseDTO;
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
}
