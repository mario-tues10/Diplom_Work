package com.example.votingSystem.controllers;

import com.example.votingSystem.models.*;
import com.example.votingSystem.services.CandidateService;
import com.example.votingSystem.services.ElectionService;
import com.example.votingSystem.services.PartyService;
import com.example.votingSystem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class VotingController {

    private final ElectionService electionService;

    private final PartyService partyService;

    private final UserService userService;

    private final CandidateService candidateService;

    private final BCryptPasswordEncoder encoder;

    @GetMapping("/elections")
    public String displayElections(Model model){
        model.addAttribute("elections", electionService.currElections());
        return "elections";
    }

    @GetMapping("/elections/{id}/performVote")
    public String displayParties(@PathVariable("id") String electionId, Model model){
        Election curr = electionService.getElectionById(Long.parseLong(electionId));
        model.addAttribute("parties", curr.getParties());
        return "performVote";
    }

    @PostMapping("/performVote/{partyId}/")
    public String voteProcess(@PathVariable Long partyId, @AuthenticationPrincipal UserPrincipal userPrincipal,
    @RequestParam Long candidateId, Model model){
        Party party = partyService.getPartyById(partyId);
        Candidate candidate = candidateService.getCandidateById(candidateId);
        User curr = userService.getUser(userPrincipal.getUsername());
        if(userService.isUserVotedForElection(curr, party.getCurrElection())) {
            return "redirect:/alreadyVoted";
        }
        Vote vote = electionService.vote(curr, candidate, party);
        model.addAttribute("voteId", vote.getId());
        return "assignedVote";
    }

    @PostMapping("/checkVote")
    public String processShowingVote(@ModelAttribute("PIN") String pin, @ModelAttribute("voteId") String voteId,
                                     @AuthenticationPrincipal UserPrincipal userPrincipal, Model model){

        if(!userPrincipal.getUser().getPIN().equals(pin)){
            return "redirect:/checkVote";
        }
        Vote curr = partyService.getVoteById(Long.parseLong(voteId));
        if(!encoder.matches(pin, curr.getHashed_PIN())){
            return "redirect:/checkVote";
        }
        Party party = curr.getVotedParty();
        Candidate candidate = curr.getVotedCandidate();
        model.addAttribute("partyName", party.getName());
        model.addAttribute("candidateName", candidate.getName());
        return "showVote";
    }

}
