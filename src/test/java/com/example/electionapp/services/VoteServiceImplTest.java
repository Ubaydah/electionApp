package com.example.electionapp.services;

import com.example.electionapp.data.models.Candidate;
import com.example.electionapp.data.models.Vote;
import com.example.electionapp.data.models.Voter;
import com.example.electionapp.data.repositories.CandidateRepository;
import com.example.electionapp.data.repositories.VoteRepository;
import com.example.electionapp.data.repositories.VoterRepository;
import com.example.electionapp.dtos.requests.CastVoteRequest;
import com.example.electionapp.dtos.responses.VoteResponse;
import com.example.electionapp.exceptions.AlreadyVotedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class VoteServiceImplTest {

    private VoteRepository voteRepository;
    private VoterRepository voterRepository;
    private CandidateRepository candidateRepository;
    private VoteService voteService;

    @BeforeEach
    void setUp() {
        voteRepository = Mockito.mock(VoteRepository.class);
        voterRepository = Mockito.mock(VoterRepository.class);
        candidateRepository = Mockito.mock(CandidateRepository.class);
        voteService = new VoteServiceImpl(voteRepository, voterRepository, candidateRepository);
    }

    @Test
    void castVote_successfully() {
        CastVoteRequest request = new CastVoteRequest();
        request.setVoterId("v1");
        request.setCandidateId("c1");

        Voter voter = Voter.builder()
                .id("v1")
                .fullName("John")
                .hasVoted(false)
                .build();

        Candidate candidate = Candidate.builder()
                .id("c1")
                .name("Alice")
                .voteCount(0)
                .build();

        when(voterRepository.findById("v1")).thenReturn(Optional.of(voter));
        when(candidateRepository.findById("c1")).thenReturn(Optional.of(candidate));
        when(voteRepository.existsByVoterId("v1")).thenReturn(false);
        when(voteRepository.save(any(Vote.class))).thenReturn(new Vote());

        VoteResponse response = voteService.castVote(request);

        assertEquals("Vote cast successfully", response.getMessage());
        assertEquals(1, candidate.getVoteCount());
    }

    @Test
    void castVote_throwsException_whenVoterAlreadyVoted() {
        CastVoteRequest request = new CastVoteRequest();
        request.setVoterId("v1");
        request.setCandidateId("c1");

        Voter voter = Voter.builder()
                .id("v1")
                .hasVoted(true)
                .build();

        Candidate candidate = Candidate.builder()
                .id("c1")
                .build();

        when(voterRepository.findById("v1")).thenReturn(Optional.of(voter));
        when(candidateRepository.findById("c1")).thenReturn(Optional.of(candidate));

        assertThrows(AlreadyVotedException.class,
                () -> voteService.castVote(request));
    }
}
