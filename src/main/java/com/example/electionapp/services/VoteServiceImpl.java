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
import com.example.electionapp.exceptions.CandidateNotFoundException;
import com.example.electionapp.exceptions.VoterNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final VoterRepository voterRepository;
    private final CandidateRepository candidateRepository;

    @Override
    public VoteResponse castVote(CastVoteRequest request) {
        Voter voter = voterRepository.findById(request.getVoterId())
                .orElseThrow(() -> new VoterNotFoundException("Voter not found"));

        Candidate candidate = candidateRepository.findById(request.getCandidateId())
                .orElseThrow(() -> new CandidateNotFoundException("Candidate not found"));

        if (voter.isHasVoted() || voteRepository.existsByVoterId(voter.getId())) {
            throw new AlreadyVotedException("This voter has already voted");
        }

        Vote vote = Vote.builder()
                .voterId(voter.getId())
                .candidateId(candidate.getId())
                .build();

        voteRepository.save(vote);

        voter.setHasVoted(true);
        voterRepository.save(voter);

        candidate.setVoteCount(candidate.getVoteCount() + 1);
        candidateRepository.save(candidate);

        return VoteResponse.builder()
                .voterId(voter.getId())
                .candidateId(candidate.getId())
                .message("Vote cast successfully")
                .build();
    }
}
