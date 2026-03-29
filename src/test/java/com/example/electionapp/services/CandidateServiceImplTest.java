package com.example.electionapp.services;

import com.example.electionapp.data.models.Candidate;
import com.example.electionapp.data.repositories.CandidateRepository;
import com.example.electionapp.dtos.requests.RegisterCandidateRequest;
import com.example.electionapp.dtos.responses.CandidateResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CandidateServiceImplTest {

    private CandidateRepository candidateRepository;
    private CandidateService candidateService;

    @BeforeEach
    void setUp() {
        candidateRepository = Mockito.mock(CandidateRepository.class);
        candidateService = new CandidateServiceImpl(candidateRepository);
    }

    @Test
    void registerCandidate_successfully() {
        RegisterCandidateRequest request = new RegisterCandidateRequest();
        request.setName("Alice");
        request.setParty("ABC Party");

        when(candidateRepository.save(any(Candidate.class))).thenAnswer(invocation -> {
            Candidate candidate = invocation.getArgument(0);
            candidate.setId("10");
            return candidate;
        });

        CandidateResponse response = candidateService.registerCandidate(request);

        assertEquals("Alice", response.getName());
        assertEquals("ABC Party", response.getParty());
        assertEquals(0, response.getVoteCount());
    }
}
