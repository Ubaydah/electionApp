package com.example.electionapp.services;

import com.example.electionapp.dtos.requests.RegisterCandidateRequest;
import com.example.electionapp.dtos.responses.CandidateResponse;
import com.example.electionapp.dtos.responses.ElectionResultResponse;

import java.util.List;

public interface CandidateService {
    CandidateResponse registerCandidate(RegisterCandidateRequest request);
    List<ElectionResultResponse> getElectionResults();
}
