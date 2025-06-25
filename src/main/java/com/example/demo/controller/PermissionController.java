package com.example.demo.controller;

import com.example.demo.dto.PermissionRequestDTO;
import com.example.demo.dto.PermissionResponseDTO;
import com.example.demo.services.PermissionService;
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
@RequestMapping("api/v1/permissions")
@RequiredArgsConstructor
public class PermissionController {

    private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);

    private final PermissionService permissionService;

    @GetMapping("/status")
    public String status() {
        logger.info("Status check requested for PermissionController");
        return "Permission controller running";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PermissionResponseDTO> create(@RequestBody @Valid PermissionRequestDTO dto) {
        logger.info("Creating permission with name: {}", dto.name());
        PermissionResponseDTO created = permissionService.createPermission(dto);
        logger.info("Permission created with id: {}", created.id());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PermissionResponseDTO> update(@PathVariable Long id, @RequestBody @Valid PermissionRequestDTO dto) {
        logger.info("Updating permission with id: {}", id);
        PermissionResponseDTO updated = permissionService.updatePermission(id, dto);
        logger.info("Permission with id {} updated", id);
        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<PermissionResponseDTO>> getAll() {
        logger.info("Fetching all permissions");
        List<PermissionResponseDTO> permissions = permissionService.getAllPermissions();
        logger.info("Found {} permissions", Optional.of(permissions.size()));
        return ResponseEntity.ok(permissions);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<PermissionResponseDTO> getById(@PathVariable Long id) {
        logger.info("Fetching permission with id: {}", id);
        PermissionResponseDTO permission = permissionService.getPermissionById(id);
        logger.info("Permission with id {} retrieved", id);
        return ResponseEntity.ok(permission);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        logger.info("Deleting permission with id: {}", id);
        permissionService.deletePermission(id);
        logger.info("Permission with id {} deleted", id);
        return ResponseEntity.noContent().build();
    }
}
