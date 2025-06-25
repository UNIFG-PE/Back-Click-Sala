package com.example.demo.controller;

import com.example.demo.dto.UserRegisterRequestDTO;
import com.example.demo.dto.UserRegisterResponseDTO;
import com.example.demo.entities.Enum.UserStatus;
import com.example.demo.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<UserRegisterResponseDTO> createUser(@RequestBody @Valid UserRegisterRequestDTO dto) {
        UserRegisterResponseDTO created = userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRegisterResponseDTO> updateUser(
            @PathVariable Long id,
            @RequestBody @Valid UserRegisterRequestDTO dto) {
        logger.info("Request received to update user with id: {}", id);
        UserRegisterResponseDTO updated = userService.updateUser(id, dto);
        logger.info("User with id {} updated", id);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        logger.info("Request received to delete user with id: {}", id);
        userService.deleteUserById(id);
        logger.info("User with id {} deleted", id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserRegisterResponseDTO>> getAllUsers() {
        logger.info("Request received to get all users");
        List<UserRegisterResponseDTO> users = userService.getAllUsers();
        logger.info("Returning {} users", Optional.of(users.size()));
        return ResponseEntity.ok(users);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserRegisterResponseDTO> getUserById(@PathVariable Long id) {
        logger.info("Request received to get user by id: {}", id);
        UserRegisterResponseDTO user = userService.getUserById(id);
        logger.info("Returning user with id: {}", id);
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search")
    public ResponseEntity<List<UserRegisterResponseDTO>> searchUsersByName(@RequestParam String name) {
        logger.info("Request received to search users by name containing: {}", name);
        List<UserRegisterResponseDTO> users = userService.findByName(name);
        logger.info("Found {} users matching name '{}'", Optional.of(users.size()), name);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/check-email")
    public ResponseEntity<Boolean> emailExists(@RequestParam String email) {
        logger.info("Checking if email exists: {}", email);
        Boolean exists = (Boolean) userService.emailExists(email);
        logger.info("Email {} exists: {}", email, exists);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/check-cpf")
    public ResponseEntity<Boolean> cpfExists(@RequestParam String cpf) {
        logger.info("Checking if CPF exists: {}", cpf);
        Boolean exists = (Boolean) userService.cpfExists(cpf);
        logger.info("CPF {} exists: {}", cpf, exists);
        return ResponseEntity.ok(exists);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> changeUserStatus(
            @PathVariable Long id,
            @RequestParam UserStatus status) {
        logger.info("Request received to change status of user with id {} to {}", id, status);
        userService.changeStatus(id, status);
        logger.info("User with id {} status changed to {}", id, status);
        return ResponseEntity.noContent().build();
    }

}