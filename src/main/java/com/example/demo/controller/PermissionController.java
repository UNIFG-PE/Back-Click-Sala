package com.example.demo.controller;

import com.example.demo.dto.PermissionRequestDTO;
import com.example.demo.dto.PermissionResponseDTO;
import com.example.demo.services.PermissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @GetMapping("/status")
    public String status (){
        return "Permission controller running";
    }

    @PostMapping
    public ResponseEntity<PermissionResponseDTO> create(@RequestBody @Valid PermissionRequestDTO dto) {
        return ResponseEntity.ok(permissionService.createPermission(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermissionResponseDTO> update(@PathVariable Long id, @RequestBody @Valid PermissionRequestDTO dto) {
        return ResponseEntity.ok(permissionService.updatePermission(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<PermissionResponseDTO>> getAll() {
        return ResponseEntity.ok(permissionService.getAllPermissions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(permissionService.getPermissionById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        permissionService.deletePermission(id);
        return ResponseEntity.noContent().build();
    }
}
