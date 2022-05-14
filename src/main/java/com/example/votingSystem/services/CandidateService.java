package com.example.votingSystem.services;

import com.example.votingSystem.models.Candidate;
import com.example.votingSystem.models.Party;
import com.example.votingSystem.repositories.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepository candidateRepo;

    private final PartyService partyService;

    @Transactional
    public void createCandidate(String name, Long partyId){
        Candidate candidate = new Candidate();
        candidate.setName(name);
        Party currParty = partyService.getPartyById(partyId);
        candidate.setCurrParty(currParty);
        currParty.getPartyPeople().add(candidate);
        candidateRepo.save(candidate);
    }


}
