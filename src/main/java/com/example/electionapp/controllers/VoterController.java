package com.example.electionapp.controllers;

import com.example.electionapp.dtos.requests.RegisterVoterRequest;
import com.example.electionapp.dtos.responses.ApiResponse;
import com.example.electionapp.services.VoterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/voters")
@RequiredArgsConstructor
public class VoterController {

    private final VoterService voterService;

    @PostMapping
    public ApiResponse<?> registerVoter(@RequestBody RegisterVoterRequest request) {
        return new ApiResponse<>(true, "Voter registered successfully",
                voterService.registerVoter(request));
    }
}
