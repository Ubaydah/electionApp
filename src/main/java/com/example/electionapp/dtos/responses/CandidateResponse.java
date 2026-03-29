package com.example.electionapp.dtos.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CandidateResponse {
    private String id;
    private String name;
    private String party;
    private int voteCount;
}
