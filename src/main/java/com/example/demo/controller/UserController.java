package com.example.demo.controller;

import com.example.demo.dto.UserRegisterRequestDTO;
import com.example.demo.dto.UserRegisterResponseDTO;
import com.example.demo.entities.Enum.UserStatus;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

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
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<List<UserRegisterResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserRegisterResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @GetMapping("/search")
    public ResponseEntity<List<UserRegisterResponseDTO>> searchUsersByName(@RequestParam String name) {
        return ResponseEntity.ok(userService.findByName(name));
    }
    @GetMapping("/check-email")
    public ResponseEntity<Boolean> emailExists(@RequestParam String email) {
        return ResponseEntity.ok(userService.emailExists(email));
    }
    @GetMapping("/check-cpf")
    public ResponseEntity<Boolean> cpfExists(@RequestParam String cpf) {
        return ResponseEntity.ok(userService.cpfExists(cpf));
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> changeUserStatus(
            @PathVariable Long id,
            @RequestParam UserStatus status) {
        userService.changeStatus(id, status);
        return ResponseEntity.noContent().build();
    }


}
