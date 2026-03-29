package com.example.electionapp.dtos.requests;

import lombok.Data;

@Data
public class RegisterVoterRequest {
    private String fullName;
    private String email;
    private String voterCardNumber;
}
