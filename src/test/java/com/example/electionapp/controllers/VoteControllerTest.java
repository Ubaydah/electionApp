package com.example.electionapp.controllers;

import com.example.electionapp.dtos.responses.VoteResponse;
import com.example.electionapp.services.VoteService;
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

@WebMvcTest(VoteController.class)
class VoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VoteService voteService;

    @Test
    void castVote_returnsSuccessResponse() throws Exception {
        VoteResponse response = VoteResponse.builder()
                .voterId("v1")
                .candidateId("c1")
                .message("Vote cast successfully")
                .build();

        when(voteService.castVote(any())).thenReturn(response);

        mockMvc.perform(post("/api/votes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "voterId": "v1",
                                  "candidateId": "c1"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.message").value("Vote cast successfully"));
    }
}
