package com.example.demo.dto.mapper;

import com.example.demo.dto.UserRegisterRequestDTO;
import com.example.demo.dto.UserRegisterResponseDTO;
import com.example.demo.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "status", constant = "ACTIVE")
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "supportTicket", ignore = true)
    @Mapping(target = "photoUrl", ignore = true)
    User toEntity(UserRegisterRequestDTO dto);

    UserRegisterResponseDTO toResponseDTO(User user);
}
