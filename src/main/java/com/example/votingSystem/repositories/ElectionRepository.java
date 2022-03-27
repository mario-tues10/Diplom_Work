package com.example.votingSystem.repositories;

import com.example.votingSystem.models.Election;
import com.example.votingSystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ElectionRepository extends JpaRepository<Election, Long> {
    Set<User> findAllUsersHere(String ID);
}