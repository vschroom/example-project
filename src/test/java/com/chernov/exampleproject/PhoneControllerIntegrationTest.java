package com.chernov.exampleproject;

import com.chernov.exampleproject.model.dto.PhoneCreateRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PhoneControllerIntegrationTest extends ControllerIntegrationTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @WithMockUser
    void shouldCreatePhoneToUser() throws Exception {
        mockMvc.perform(post("/api/v1/phone")
                        .content(objectMapper.writeValueAsString(new PhoneCreateRequest(1L, "78776663352")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(5L))
                .andExpect(jsonPath("$.phone").value("78776663352"))
                .andExpect(jsonPath("$.userId").value(1L));
    }
}
