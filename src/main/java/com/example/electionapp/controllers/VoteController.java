package com.example.electionapp.controllers;

import com.example.electionapp.dtos.requests.CastVoteRequest;
import com.example.electionapp.dtos.responses.ApiResponse;
import com.example.electionapp.services.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/votes")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping
    public ApiResponse<?> castVote(@RequestBody CastVoteRequest request) {
        return new ApiResponse<>(true, "Vote cast successfully",
                voteService.castVote(request));
    }
}
