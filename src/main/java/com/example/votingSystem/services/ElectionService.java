package com.example.votingSystem.services;

import com.example.votingSystem.models.*;
import com.example.votingSystem.repositories.ElectionRepository;
import com.example.votingSystem.repositories.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ElectionService {

    private final ElectionRepository electionRepo;

    private final UserService userService;

    private final VoteRepository voteRepository;

    private final BCryptPasswordEncoder encoder;

    @Transactional
    public void createElection( String name, LocalDateTime begin, LocalDateTime end){
        Election curr = new Election();
        curr.setName(name);
        curr.setBeginning(begin);
        curr.setEnd(end);
        electionRepo.save(curr);
    }

    public List<Election> currElections() {

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

    public Election getElectionById(Long id){
        return electionRepo.getById(id);
    }

    @Transactional
    public Vote vote(User user, Candidate candidate, Party party){
        saveElectionToUser(user, party);
        return createVote(party, user.getPIN(), candidate);
    }

    private void saveElectionToUser(User user, Party party){
        Election election = party.getCurrElection();
        user.getElections().add(election);
        userService.saveUser(user);
    }

    private Vote createVote(Party party, String pin, Candidate candidate){
        Vote curr = new Vote();
        curr.setVotedParty(party);
        curr.setVotedCandidate(candidate);
        curr.setHashed_PIN(encoder.encode(pin));
        return voteRepository.save(curr);
    }
}
