package com.example.demo.services;

import com.example.demo.dto.UserRegisterRequestDTO;
import com.example.demo.dto.UserRegisterResponseDTO;
import com.example.demo.dto.mapper.UserMapper;
import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public UserRegisterResponseDTO createUser(UserRegisterRequestDTO userRegisterRequestDTO) {
        return userMapper.toResponseDTO(userRepository.save(userMapper.toEntity(userRegisterRequestDTO)));
    }

}
