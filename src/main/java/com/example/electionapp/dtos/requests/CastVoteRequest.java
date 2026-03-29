package com.example.electionapp.dtos.requests;

import lombok.Data;

@Data
public class CastVoteRequest {
    private String voterId;
    private String candidateId;
}
