package com.example.electionapp.controllers;

import com.example.electionapp.dtos.requests.RegisterCandidateRequest;
import com.example.electionapp.dtos.responses.ApiResponse;
import com.example.electionapp.services.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping
    public ApiResponse<?> registerCandidate(@RequestBody RegisterCandidateRequest request) {
        return new ApiResponse<>(true, "Candidate registered successfully",
                candidateService.registerCandidate(request));
    }

    @GetMapping("/results")
    public ApiResponse<?> getResults() {
        return new ApiResponse<>(true, "Election results fetched successfully",
                candidateService.getElectionResults());
    }
}
