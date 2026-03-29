package com.example.electionapp.exceptions;

public class CandidateNotFoundException extends ElectionAppException {
    public CandidateNotFoundException(String message) {
        super(message);
    }
}
