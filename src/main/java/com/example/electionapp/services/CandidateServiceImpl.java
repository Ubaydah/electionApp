package com.example.electionapp.services;

import com.example.electionapp.data.models.Candidate;
import com.example.electionapp.data.repositories.CandidateRepository;
import com.example.electionapp.dtos.requests.RegisterCandidateRequest;
import com.example.electionapp.dtos.responses.CandidateResponse;
import com.example.electionapp.dtos.responses.ElectionResultResponse;
import com.example.electionapp.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    @Override
    public CandidateResponse registerCandidate(RegisterCandidateRequest request) {
        Candidate candidate = Candidate.builder()
                .name(request.getName())
                .party(request.getParty())
                .voteCount(0)
                .build();

        return Mapper.mapCandidate(candidateRepository.save(candidate));
    }

    @Override
    public List<ElectionResultResponse> getElectionResults() {
        return candidateRepository.findAll()
                .stream()
                .map(Mapper::mapResult)
                .toList();
    }
}
