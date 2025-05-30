package com.example.demo.services;

import com.example.demo.dto.UserResponseDTO;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponseDTO> findAll() {
        return userRepository.findAll().stream()
                .map(UserResponseDTO::new)
                .toList();
    }
}
