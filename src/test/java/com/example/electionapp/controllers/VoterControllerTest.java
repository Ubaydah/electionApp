package com.example.electionapp.controllers;

import com.example.electionapp.dtos.responses.VoterResponse;
import com.example.electionapp.services.VoterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VoterController.class)
class VoterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VoterService voterService;

    @Test
    void registerVoter_returnsCreatedResponse() throws Exception {
        VoterResponse response = VoterResponse.builder()
                .id("1")
                .fullName("John Doe")
                .email("john@gmail.com")
                .voterCardNumber("VC123")
                .hasVoted(false)
                .build();

        when(voterService.registerVoter(any())).thenReturn(response);

        mockMvc.perform(post("/api/voters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "fullName": "John Doe",
                                  "email": "john@gmail.com",
                                  "voterCardNumber": "VC123"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.fullName").value("John Doe"));
    }
}
