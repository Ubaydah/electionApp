package com.example.electionapp.dtos.requests;

import lombok.Data;

@Data
public class RegisterCandidateRequest {
    private String name;
    private String party;
}
