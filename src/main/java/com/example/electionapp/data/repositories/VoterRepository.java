package com.example.electionapp.data.repositories;

import com.example.electionapp.data.models.Voter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VoterRepository extends MongoRepository<Voter, String> {
    Optional<Voter> findByEmail(String email);
    Optional<Voter> findByVoterCardNumber(String voterCardNumber);
}
