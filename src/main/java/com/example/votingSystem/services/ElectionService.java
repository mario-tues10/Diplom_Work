package com.example.votingSystem.services;

import com.example.votingSystem.models.Election;
import com.example.votingSystem.repositories.ElectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ElectionService {

    @Autowired
    ElectionRepository electionRepo;

    @Transactional
    public void createElection( String name, LocalDateTime begin, LocalDateTime end){
        Election curr = new Election();
        curr.setName(name);
        curr.setBeginning(begin);
        curr.setEnd(end);
        electionRepo.save(curr);
    }

    public List<Election> currElections() {

        LocalDateTime now = LocalDateTime.now();
        List<Election> available = new ArrayList<>();
        for(Election curr : electionRepo.findAll()){
            if(isItAvailable(curr)){
                available.add(curr);
            }
        }

        return available;
    }

    private boolean isItAvailable(Election curr){
        LocalDateTime now = LocalDateTime.now();
        return now.isBefore(curr.getEnd()) && now.isAfter(curr.getBeginning());
    }




}
