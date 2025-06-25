package com.example.demo.controller;

import com.example.demo.dto.PermissionRequestDTO;
import com.example.demo.dto.PermissionResponseDTO;
import com.example.demo.services.PermissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PermissionResponseDTO> create(@RequestBody @Valid PermissionRequestDTO dto) {
        return ResponseEntity.ok(permissionService.createPermission(dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PermissionResponseDTO> update(@PathVariable Long id, @RequestBody @Valid PermissionRequestDTO dto) {
        return ResponseEntity.ok(permissionService.updatePermission(id, dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<PermissionResponseDTO>> getAll() {
        return ResponseEntity.ok(permissionService.getAllPermissions());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<PermissionResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(permissionService.getPermissionById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        permissionService.deletePermission(id);
        return ResponseEntity.noContent().build();
    }
}
