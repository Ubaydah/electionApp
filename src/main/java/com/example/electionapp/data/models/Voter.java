package com.example.electionapp.data.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "voters")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Voter {
    @Id
    private String id;
    private String fullName;
    private String email;
    private String voterCardNumber;
    private boolean hasVoted;
}
