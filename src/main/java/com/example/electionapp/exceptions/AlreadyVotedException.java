package com.example.electionapp.exceptions;

public class AlreadyVotedException extends ElectionAppException {
    public AlreadyVotedException(String message) {
        super(message);
    }
}
