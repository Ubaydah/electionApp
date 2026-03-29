package com.example.electionapp.services;

import com.example.electionapp.dtos.requests.CastVoteRequest;
import com.example.electionapp.dtos.responses.VoteResponse;

public interface VoteService {
    VoteResponse castVote(CastVoteRequest request);
}
