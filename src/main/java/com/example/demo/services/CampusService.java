package com.example.demo.services;

import com.example.demo.entities.Campus;
import com.example.demo.repository.CampusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampusService {

    private final CampusRepository campusRepository;

    public List<Campus> findAll() {
        return campusRepository.findAll();
    }

    public Campus createCampus (Campus campus) {
        return campusRepository.save(campus);
    }

    public Campus updateCampus (Long id, Campus campus) {
        Campus existingCampus = campusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campus not found"));

        existingCampus.setName(campus.getName());
        existingCampus.setAddress(campus.getAddress());

        return campusRepository.save(existingCampus);
    }

    public void delete (Long id) {
        campusRepository.deleteById(id);
    }
}
