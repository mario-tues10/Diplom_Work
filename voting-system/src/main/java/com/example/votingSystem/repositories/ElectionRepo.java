package com.example.votingSystem.repositories;


import com.example.votingSystem.models.Election;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ElectionRepo extends JpaRepository<Election, Long> {


}
