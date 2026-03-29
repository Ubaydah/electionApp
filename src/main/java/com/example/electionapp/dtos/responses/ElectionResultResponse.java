package com.example.electionapp.dtos.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ElectionResultResponse {
    private String candidateId;
    private String candidateName;
    private String party;
    private int totalVotes;
}
