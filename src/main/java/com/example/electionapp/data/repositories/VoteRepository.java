package com.example.electionapp.data.repositories;

import com.example.electionapp.data.models.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VoteRepository extends MongoRepository<Vote, String> {
    boolean existsByVoterId(String voterId);
}
