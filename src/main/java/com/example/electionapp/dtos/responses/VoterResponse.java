package com.example.electionapp.dtos.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VoterResponse {
    private String id;
    private String fullName;
    private String email;
    private String voterCardNumber;
    private boolean hasVoted;
}
