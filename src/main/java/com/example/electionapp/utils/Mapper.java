package com.example.electionapp.utils;

import com.example.electionapp.data.models.Candidate;
import com.example.electionapp.data.models.Voter;
import com.example.electionapp.dtos.responses.CandidateResponse;
import com.example.electionapp.dtos.responses.ElectionResultResponse;
import com.example.electionapp.dtos.responses.VoterResponse;

public class Mapper {

    private Mapper() {
    }

    public static CandidateResponse mapCandidate(Candidate candidate) {
        return CandidateResponse.builder()
                .id(candidate.getId())
                .name(candidate.getName())
                .party(candidate.getParty())
                .voteCount(candidate.getVoteCount())
                .build();
    }

    public static VoterResponse mapVoter(Voter voter) {
        return VoterResponse.builder()
                .id(voter.getId())
                .fullName(voter.getFullName())
                .email(voter.getEmail())
                .voterCardNumber(voter.getVoterCardNumber())
                .hasVoted(voter.isHasVoted())
                .build();
    }

    public static ElectionResultResponse mapResult(Candidate candidate) {
        return ElectionResultResponse.builder()
                .candidateId(candidate.getId())
                .candidateName(candidate.getName())
                .party(candidate.getParty())
                .totalVotes(candidate.getVoteCount())
                .build();
    }
}
