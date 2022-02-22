package com.example.votingSystem.repositories;

import com.example.votingSystem.models.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartyRepo extends JpaRepository<Party, Long> {

}
