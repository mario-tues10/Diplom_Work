package com.example.votingSystem.controllers;

import com.example.votingSystem.services.ElectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.time.LocalDateTime;
import java.time.Month;

@Controller
public class ElectionController {

    @Autowired
    ElectionService electionService;

    @GetMapping("/elections")
    public String displayElections(Model model){
        electionService.createElection(  "President Elections", LocalDateTime.of(2022, Month.MARCH,
                17, 0, 0, 0), LocalDateTime.of(2022, Month.MARCH,
                18, 0, 0, 0)
        );
        electionService.createElection( "Parliamentary Elections", LocalDateTime.of(2022, Month.MARCH,
                10, 0, 0, 0), LocalDateTime.of(2022, Month.MARCH,
                12, 0, 0, 0));
        electionService.createElection( "Past Elections", LocalDateTime.of(2021, Month.MARCH,
                16, 0, 0, 0), LocalDateTime.of(2021, Month.MARCH,
                19, 0, 0, 0));
        model.addAttribute("elections", electionService.currElections());
        return "elections";
    }


}
