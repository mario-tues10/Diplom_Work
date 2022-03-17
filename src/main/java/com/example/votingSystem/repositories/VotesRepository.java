package com.example.votingSystem.repositories;

import com.example.votingSystem.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotesRepository extends JpaRepository<Vote, Long> {
}