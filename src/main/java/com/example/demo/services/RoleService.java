package com.example.demo.services;

import com.example.demo.dto.PermissionResponseDTO;
import com.example.demo.dto.RoleRequestDTO;
import com.example.demo.dto.RoleResponseDTO;
import com.example.demo.entities.Permission;
import com.example.demo.entities.Role;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Transactional
    public RoleResponseDTO createRole(RoleRequestDTO dto) {
        Set<Permission> permissions = fetchPermissionsByIds(dto.permissionIds());

        Role role = new Role();
        role.setName(dto.name());
        role.setPermissions(permissions);

        Role savedRole = roleRepository.save(role);
        return toResponseDTO(savedRole);
    }

    @Transactional
    public RoleResponseDTO updateRole(Long id, RoleRequestDTO dto) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with ID: " + id));

        role.setName(dto.name());
        role.setPermissions(fetchPermissionsByIds(dto.permissionIds()));

        return toResponseDTO(roleRepository.save(role));
    }

    @Transactional
    public List<RoleResponseDTO> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional
    public RoleResponseDTO getRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with ID: " + id));
        return toResponseDTO(role);
    }

    @Transactional
    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new EntityNotFoundException("Role not found with ID: " + id);
        }
        roleRepository.deleteById(id);
    }

    private Set<Permission> fetchPermissionsByIds(Set<Long> permissionIds) {
        Set<Permission> permissions = new HashSet<>();
        if (permissionIds != null) {
            for (Long id : permissionIds) {
                Permission permission = permissionRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Permission not found with ID: " + id));
                permissions.add(permission);
            }
        }
        return permissions;
    }

    private RoleResponseDTO toResponseDTO(Role role) {
        Set<PermissionResponseDTO> permissionDTOs = role.getPermissions().stream()
                .map(permission -> new PermissionResponseDTO(
                        permission.getId(),
                        permission.getName(),
                        permission.getDescription()
                )).collect(Collectors.toSet());

        return new RoleResponseDTO(
                role.getId(),
                role.getName(),
                permissionDTOs
        );
    }

}
