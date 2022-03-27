package com.example.votingSystem.services;

import com.example.votingSystem.models.Election;
import com.example.votingSystem.models.Party;
import com.example.votingSystem.repositories.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PartyService {

    @Autowired
    private PartyRepository partyRepo;

    @Autowired
    private ElectionService electionService;

    @Transactional
    public void createParty(String name, Long electionID){
        Party currParty = new Party();
        currParty.setName(name);
        Election currElection = electionService.getById(electionID);
        currParty.setCurrElection(currElection);
        currElection.getParties().add(currParty);
        partyRepo.save(currParty);
    }

    public Party getParty(Long id){
        return partyRepo.getById(id);
    }

}
