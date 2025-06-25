package com.example.demo.controller;

import com.example.demo.dto.RoleRequestDTO;
import com.example.demo.dto.RoleResponseDTO;
import com.example.demo.services.RoleService;
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
@RequestMapping("api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    private final RoleService roleService;

    @GetMapping("/status")
    public String status() {
        logger.info("Status check requested for RoleController");
        return "Role controller running";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<RoleResponseDTO> create(@RequestBody @Valid RoleRequestDTO dto) {
        logger.info("Creating role with name: {}", dto.name());
        RoleResponseDTO created = roleService.createRole(dto);
        logger.info("Role created with id: {}", created.id());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> update(@PathVariable Long id, @RequestBody @Valid RoleRequestDTO dto) {
        logger.info("Updating role with id: {}", id);
        RoleResponseDTO updated = roleService.updateRole(id, dto);
        logger.info("Role with id {} updated", id);
        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<RoleResponseDTO>> getAll() {
        logger.info("Fetching all roles");
        List<RoleResponseDTO> roles = roleService.getAllRoles();
        logger.info("Found {} roles", Optional.of(roles.size()));
        return ResponseEntity.ok(roles);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> getById(@PathVariable Long id) {
        logger.info("Fetching role with id: {}", id);
        RoleResponseDTO role = roleService.getRoleById(id);
        logger.info("Role with id {} retrieved", id);
        return ResponseEntity.ok(role);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        logger.info("Deleting role with id: {}", id);
        roleService.deleteRole(id);
        logger.info("Role with id {} deleted", id);
        return ResponseEntity.noContent().build();
    }
}
