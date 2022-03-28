package com.example.votingSystem.controllers;

import com.example.votingSystem.models.Election;
import com.example.votingSystem.models.Party;
import com.example.votingSystem.models.User;
import com.example.votingSystem.models.UserPrincipal;
import com.example.votingSystem.models.Vote;
import com.example.votingSystem.services.ElectionService;
import com.example.votingSystem.services.PartyService;
import com.example.votingSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VotingController {

    @Autowired
    ElectionService electionService;

    @Autowired
    PartyService partyService;

    @Autowired
    UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private Long voteId;

    private String partyName;

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
    public String voteProcess(@PathVariable Long partyId, @AuthenticationPrincipal UserPrincipal userPrincipal){
        Party party = partyService.getParty(partyId);
        User curr = userService.getUser(userPrincipal.getUsername());
        if(userService.isUserVotedForElection(curr, party.getCurrElection())) {
            return "redirect:/alreadyVoted";
        }
        Vote vote = electionService.vote(curr, party);
        voteId = vote.getId();
        return "redirect:/assignedVote";
    }

    @GetMapping("/assignedVote")
    public String ready(Model model){
        model.addAttribute("voteId", voteId);
        return "assignedVote";
    }

    @GetMapping("/checkVote")
    public String showCheck(Model model){
        model.addAttribute("PIN", null);
        model.addAttribute("voteId", null);
        return "checkVote";
    }

    @PostMapping("/checkVote")
    public String processShowingVote(@ModelAttribute("PIN") String pin, @ModelAttribute("voteId") String voteId,
                                     @AuthenticationPrincipal UserPrincipal userPrincipal){

        if(!userPrincipal.getUser().getPIN().equals(pin)){
            return "redirect:/checkVote";
        }
        Vote curr = partyService.getVoteById(Long.parseLong(voteId));
        if(!encoder.matches(pin, curr.getHashed_PIN())){
            return "redirect:/checkVote";
        }
        Party party = curr.getVotedParty();
        partyName = party.getName();
        return "redirect:/showVote";
    }

    @GetMapping("/showVote")
    public String showVote(Model model){
        model.addAttribute("partyName", partyName);
        return "showVote";
    }

}
