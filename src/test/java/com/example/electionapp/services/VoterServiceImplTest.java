package com.example.electionapp.services;

import com.example.electionapp.data.models.Voter;
import com.example.electionapp.data.repositories.VoterRepository;
import com.example.electionapp.dtos.requests.RegisterVoterRequest;
import com.example.electionapp.dtos.responses.VoterResponse;
import com.example.electionapp.exceptions.DuplicateVoterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class VoterServiceImplTest {

    private VoterRepository voterRepository;
    private VoterService voterService;

    @BeforeEach
    void setUp() {
        voterRepository = Mockito.mock(VoterRepository.class);
        voterService = new VoterServiceImpl(voterRepository);
    }

    @Test
    void registerVoter_successfully() {
        RegisterVoterRequest request = new RegisterVoterRequest();
        request.setFullName("John Doe");
        request.setEmail("john@gmail.com");
        request.setVoterCardNumber("VC123");

        when(voterRepository.findByEmail("john@gmail.com")).thenReturn(Optional.empty());
        when(voterRepository.findByVoterCardNumber("VC123")).thenReturn(Optional.empty());
        when(voterRepository.save(any(Voter.class))).thenAnswer(invocation -> {
            Voter voter = invocation.getArgument(0);
            voter.setId("1");
            return voter;
        });

        VoterResponse response = voterService.registerVoter(request);

        assertEquals("John Doe", response.getFullName());
        assertEquals("john@gmail.com", response.getEmail());
        assertFalse(response.isHasVoted());
    }

    @Test
    void registerVoter_throwsException_whenEmailAlreadyExists() {
        RegisterVoterRequest request = new RegisterVoterRequest();
        request.setEmail("john@gmail.com");
        request.setVoterCardNumber("VC123");

        when(voterRepository.findByEmail("john@gmail.com"))
                .thenReturn(Optional.of(new Voter()));

        assertThrows(DuplicateVoterException.class,
                () -> voterService.registerVoter(request));
    }
}
