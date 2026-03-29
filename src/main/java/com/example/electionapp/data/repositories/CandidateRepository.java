package com.example.electionapp.data.repositories;

import com.example.electionapp.data.models.Candidate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CandidateRepository extends MongoRepository<Candidate, String> {
}
