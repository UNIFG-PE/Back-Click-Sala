package com.example.demo.services;

import com.example.demo.dto.UserRegisterRequestDTO;
import com.example.demo.dto.UserRegisterResponseDTO;
import com.example.demo.dto.mapper.UserMapper;
import com.example.demo.entities.Enum.UserStatus;
import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public UserRegisterResponseDTO createUser(UserRegisterRequestDTO userRegisterRequestDTO) {
        return userMapper.toResponseDTO(userRepository.save(userMapper.toEntity(userRegisterRequestDTO)));
    }
    @Transactional
    public UserRegisterResponseDTO updateUser(Long id, UserRegisterRequestDTO userRegisterRequestDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));

        User updatedUser = userMapper.toEntity(userRegisterRequestDTO);
        updatedUser.setId(existingUser.getId());

        return userMapper.toResponseDTO(userRepository.save(updatedUser));
    }

    @Transactional
    public List<UserRegisterResponseDTO> findByName(String name) {
        return userRepository.findByFullNameContainingIgnoreCase(name).stream()
                .map(userMapper::toResponseDTO)
                .toList();
    }

    @Transactional
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }
    @Transactional
    public List<UserRegisterResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponseDTO)
                .toList();
    }
    @Transactional
    public UserRegisterResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
        return userMapper.toResponseDTO(user);
    }

    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }
    public boolean cpfExists(String cpf) {
        return userRepository.existsByCpf(cpf);
    }
    @Transactional
    public void changeStatus(Long userId, UserStatus status) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setStatus(status);
        userRepository.save(user);
    }
}


