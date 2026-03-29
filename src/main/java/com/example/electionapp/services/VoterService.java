package com.example.electionapp.services;

import com.example.electionapp.dtos.requests.RegisterVoterRequest;
import com.example.electionapp.dtos.responses.VoterResponse;

public interface VoterService {
    VoterResponse registerVoter(RegisterVoterRequest request);
}
