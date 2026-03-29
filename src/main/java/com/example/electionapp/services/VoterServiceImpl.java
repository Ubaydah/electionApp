package com.example.electionapp.services;

import com.example.electionapp.data.models.Voter;
import com.example.electionapp.data.repositories.VoterRepository;
import com.example.electionapp.dtos.requests.RegisterVoterRequest;
import com.example.electionapp.dtos.responses.VoterResponse;
import com.example.electionapp.exceptions.DuplicateVoterException;
import com.example.electionapp.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoterServiceImpl implements VoterService {

    private final VoterRepository voterRepository;

    @Override
    public VoterResponse registerVoter(RegisterVoterRequest request) {
        if (voterRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new DuplicateVoterException("A voter with this email already exists");
        }

        if (voterRepository.findByVoterCardNumber(request.getVoterCardNumber()).isPresent()) {
            throw new DuplicateVoterException("A voter with this voter card number already exists");
        }

        Voter voter = Voter.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .voterCardNumber(request.getVoterCardNumber())
                .hasVoted(false)
                .build();

        return Mapper.mapVoter(voterRepository.save(voter));
    }
}
