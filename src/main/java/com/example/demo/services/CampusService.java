package com.example.demo.services;

import com.example.demo.repository.CampusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CampusService {

    private final CampusRepository campusRepository;

}
