package com.example.electionapp.dtos.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VoteResponse {
    private String voterId;
    private String candidateId;
    private String message;
}
