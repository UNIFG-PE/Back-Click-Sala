package com.example.demo.services;

import com.example.demo.dto.PermissionRequestDTO;
import com.example.demo.dto.PermissionResponseDTO;
import com.example.demo.entities.Permission;
import com.example.demo.repository.PermissionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionService {

    private final PermissionRepository permissionRepository;


    @Transactional
    public PermissionResponseDTO createPermission(PermissionRequestDTO dto) {
        Permission permission = new Permission();
        permission.setName(dto.name());
        permission.setDescription(dto.description());

        Permission saved = permissionRepository.save(permission);
        return toResponseDTO(saved);
    }

    @Transactional
    public PermissionResponseDTO updatePermission(Long id, PermissionRequestDTO dto) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Permission not found with ID: " + id));

        permission.setName(dto.name());
        permission.setDescription(dto.description());

        return toResponseDTO(permissionRepository.save(permission));
    }

    @Transactional
    public List<PermissionResponseDTO> getAllPermissions() {
        return permissionRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional
    public PermissionResponseDTO getPermissionById(Long id) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Permission not found with ID: " + id));
        return toResponseDTO(permission);
    }

    @Transactional
    public void deletePermission(Long id) {
        if (!permissionRepository.existsById(id)) {
            throw new EntityNotFoundException("Permission not found with ID: " + id);
        }
        permissionRepository.deleteById(id);
    }

    private PermissionResponseDTO toResponseDTO(Permission permission) {
        return new PermissionResponseDTO(
                permission.getId(),
                permission.getName(),
                permission.getDescription()
        );
    }

}
