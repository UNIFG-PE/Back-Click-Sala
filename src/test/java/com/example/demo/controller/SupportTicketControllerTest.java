package com.example.demo.controller;

import com.example.demo.dto.SupportTicketRequestDTO;
import com.example.demo.dto.SupportTicketResponseDTO;
import com.example.demo.services.SupportTicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SupportTicketController.class)
class SupportTicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SupportTicketService supportTicketService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGetAllSupportTickets() throws Exception {
        // Given
        List<SupportTicketResponseDTO> tickets = Arrays.asList(
                createSampleResponseDTO(1L, "Issue with projector"),
                createSampleResponseDTO(2L, "Air conditioning not working")
        );
        when(supportTicketService.findAll()).thenReturn(tickets);

        // When & Then
        mockMvc.perform(get("/api/v1/supporttickets"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].reason").value("Issue with projector"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].reason").value("Air conditioning not working"));
    }

    @Test
    void shouldGetSupportTicketById() throws Exception {
        // Given
        SupportTicketResponseDTO ticket = createSampleResponseDTO(1L, "Issue with projector");
        when(supportTicketService.findById(1L)).thenReturn(ticket);

        // When & Then
        mockMvc.perform(get("/api/v1/supporttickets/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.reason").value("Issue with projector"));
    }

    @Test
    void shouldCreateSupportTicket() throws Exception {
        // Given
        SupportTicketRequestDTO requestDTO = new SupportTicketRequestDTO();
        requestDTO.setReason("New issue with equipment");
        requestDTO.setRoomBookingId(1L);
        requestDTO.setAttendentId(1L);

        SupportTicketResponseDTO responseDTO = createSampleResponseDTO(1L, "New issue with equipment");
        when(supportTicketService.create(any(SupportTicketRequestDTO.class))).thenReturn(responseDTO);

        // When & Then
        mockMvc.perform(post("/api/v1/supporttickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.reason").value("New issue with equipment"));
    }

    @Test
    void shouldUpdateSupportTicket() throws Exception {
        // Given
        SupportTicketRequestDTO requestDTO = new SupportTicketRequestDTO();
        requestDTO.setReason("Updated issue description");
        requestDTO.setRoomBookingId(1L);
        requestDTO.setAttendentId(1L);

        SupportTicketResponseDTO responseDTO = createSampleResponseDTO(1L, "Updated issue description");
        when(supportTicketService.update(eq(1L), any(SupportTicketRequestDTO.class))).thenReturn(responseDTO);

        // When & Then
        mockMvc.perform(put("/api/v1/supporttickets/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.reason").value("Updated issue description"));
    }

    @Test
    void shouldDeleteSupportTicket() throws Exception {
        // When & Then
        mockMvc.perform(delete("/api/v1/supporttickets/1"))
                .andExpect(status().isNoContent());
    }

    private SupportTicketResponseDTO createSampleResponseDTO(Long id, String reason) {
        SupportTicketResponseDTO dto = new SupportTicketResponseDTO();
        dto.setId(id);
        dto.setReason(reason);
        dto.setAttendentId(1L);
        dto.setRoomBookingId(1L);
        dto.setCreatedAt(Instant.now());
        dto.setLastModifiedAt(Instant.now());
        return dto;
    }
}
