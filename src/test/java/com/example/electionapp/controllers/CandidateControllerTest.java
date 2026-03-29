package com.example.electionapp.controllers;

import com.example.electionapp.dtos.responses.CandidateResponse;
import com.example.electionapp.dtos.responses.ElectionResultResponse;
import com.example.electionapp.services.CandidateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CandidateController.class)
class CandidateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CandidateService candidateService;

    @Test
    void registerCandidate_returnsSuccessResponse() throws Exception {
        CandidateResponse response = CandidateResponse.builder()
                .id("c1")
                .name("Alice Johnson")
                .party("ABC Party")
                .voteCount(0)
                .build();

        when(candidateService.registerCandidate(any())).thenReturn(response);

        mockMvc.perform(post("/api/candidates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Alice Johnson",
                                  "party": "ABC Party"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.name").value("Alice Johnson"));
    }

    @Test
    void getResults_returnsElectionResults() throws Exception {
        ElectionResultResponse result = ElectionResultResponse.builder()
                .candidateId("c1")
                .candidateName("Alice Johnson")
                .party("ABC Party")
                .totalVotes(5)
                .build();

        when(candidateService.getElectionResults()).thenReturn(List.of(result));

        mockMvc.perform(get("/api/candidates/results"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data[0].candidateName").value("Alice Johnson"))
                .andExpect(jsonPath("$.data[0].totalVotes").value(5));
    }
}
