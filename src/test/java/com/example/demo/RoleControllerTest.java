package com.example.demo;

import com.example.demo.config.SecurityConfig;
import com.example.demo.controller.RoleController;
import com.example.demo.dto.RoleRequestDTO;
import com.example.demo.dto.RoleResponseDTO;
import com.example.demo.services.RoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest
@ContextConfiguration(classes = {RoleController.class, RoleControllerTest.TestConfig.class})
@Import(SecurityConfig.class)
public class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RoleService roleService;

    @InjectMocks
    private RoleController roleController;

    @Autowired
    private ObjectMapper objectMapper;

    @Configuration
    static class TestConfig {
        @Bean
        public RoleService roleService() {
            return org.mockito.Mockito.mock(RoleService.class);
        }
    }

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void whenCreateRoleWithAdmin_thenReturnsCreated() throws Exception {
        RoleRequestDTO requestDTO = new RoleRequestDTO("PROFESSOR_TI", Collections.emptySet());
        RoleResponseDTO responseDTO = new RoleResponseDTO(Long.valueOf(1L), "PROFESSOR_TI", Collections.emptySet());

        when(roleService.createRole(any(RoleRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/api/v1/roles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "PROFESSOR_TI",
                                  "permissionIds": []
                                }
                                """))
                .andDo(print())
                .andExpect(status().isCreated()) // <-- Espera status 201
                .andExpect(jsonPath("$.name").value("PROFESSOR_TI"));
    }
}
