package com.example.electionapp.exceptions;

public class VoterNotFoundException extends ElectionAppException {
    public VoterNotFoundException(String message) {
        super(message);
    }
}
