package com.example.votingSystem.services;

import com.example.votingSystem.models.Election;
import com.example.votingSystem.models.Party;
import com.example.votingSystem.models.User;
import com.example.votingSystem.models.Vote;
import com.example.votingSystem.repositories.PartyRepository;
import com.example.votingSystem.repositories.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class PartyService {

    private final PartyRepository partyRepo;

    private final ElectionService electionService;

    private final VoteRepository voteRepository;

    @Transactional
    public void createParty(String name, Long electionID){
        Party currParty = new Party();
        currParty.setName(name);
        Election currElection = electionService.getElectionById(electionID);
        currParty.setCurrElection(currElection);
        currElection.getParties().add(currParty);
        partyRepo.save(currParty);
    }

    public Party getPartyById(Long id){
        return partyRepo.getById(id);
    }

    public Vote getVoteById(Long id){
        return voteRepository.getById(id);
    }

}
