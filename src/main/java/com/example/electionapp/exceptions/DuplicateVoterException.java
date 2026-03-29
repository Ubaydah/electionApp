package com.example.electionapp.exceptions;

public class DuplicateVoterException extends ElectionAppException {
    public DuplicateVoterException(String message) {
        super(message);
    }
}
