package com.example.votingSystem.controllers;

import com.example.votingSystem.models.Election;
import com.example.votingSystem.services.ElectionService;
import com.example.votingSystem.services.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.time.Month;

@Controller
public class VotingController {

    @Autowired
    ElectionService electionService;

    @Autowired
    PartyService partyService;

    private void init(){
        electionService.createElection(  "President Elections", LocalDateTime.of(2022, Month.MARCH,
                26, 0, 0, 0), LocalDateTime.of(2022, Month.MARCH,
                28, 0, 0, 0)
        );
        electionService.createElection( "Parliamentary Elections", LocalDateTime.of(2022, Month.MARCH,
                10, 0, 0, 0), LocalDateTime.of(2022, Month.MARCH,
                12, 0, 0, 0));
        electionService.createElection( "Past Elections", LocalDateTime.of(2022, Month.MARCH,
                26, 0, 0, 0), LocalDateTime.of(2022, Month.MARCH,
                29, 0, 0, 0));

        partyService.createParty("GERB", 1L);
        partyService.createParty("PP", 1L);
        partyService.createParty("BSP", 1L);
        partyService.createParty("DSP", 1L);
        partyService.createParty("ATAKA", 1L);
    }

    @GetMapping("/elections")
    public String displayElections(Model model){
        init();
        model.addAttribute("elections", electionService.currElections());
        return "elections";
    }

    @GetMapping("/elections/id={id}/performVote")
    public String displayParties(@PathVariable("id") String electionId, Model model){
        Election curr = electionService.getById(Long.parseLong(electionId));
        model.addAttribute("parties", curr.getParties());
        return "performVote";
    }
/*
    @PostMapping("/performVote")
    public String voteProcess(){

    }

    @GetMapping("/checkVote")
    public String myVote(){

    }

 */
    @GetMapping("/assignedVote")
    public String ready(){
        return "assignedVote";
    }
}
