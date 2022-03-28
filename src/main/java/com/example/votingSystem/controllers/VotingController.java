package com.example.votingSystem.controllers;

import com.example.votingSystem.models.Election;
import com.example.votingSystem.models.Party;
import com.example.votingSystem.models.User;
import com.example.votingSystem.models.UserPrincipal;
import com.example.votingSystem.models.Vote;
import com.example.votingSystem.services.ElectionService;
import com.example.votingSystem.services.PartyService;
import com.example.votingSystem.services.UserService;
import java.lang.ProcessBuilder.Redirect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.time.Month;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VotingController {

    @Autowired
    ElectionService electionService;

    @Autowired
    PartyService partyService;

    @Autowired
    UserService userService;

    @GetMapping("/elections")
    public String displayElections(Model model){
        model.addAttribute("elections", electionService.currElections());
        return "elections";
    }

    @GetMapping("/elections/id={id}/performVote")
    public String displayParties(@PathVariable("id") String electionId, Model model){
        Election curr = electionService.getById(Long.parseLong(electionId));
        model.addAttribute("parties", curr.getParties());
        return "performVote";
    }

    @PostMapping("/performVote/{partyId}")
    public String voteProcess(@PathVariable Long partyId, Model model, @AuthenticationPrincipal UserPrincipal userPrincipal){
        Party party = partyService.getParty(partyId);
        User curr = userService.getUser(userPrincipal.getUsername());
        if(userService.isUserVotedForElection(curr, party.getCurrElection())){
            return "redirect:/alreadyVoted";
        }
        Vote vote = electionService.vote(curr, party);
        model.addAttribute("voteId", vote.getId());
        return "redirect:/assignedVote";
    }

    @GetMapping("/checkVote")
    public String myVote(){
        return "no";
    }


    @GetMapping("/assignedVote")
    public String ready(){
        return "assignedVote";
    }
}
