package com.example.votingSystem.customConfig;

import com.example.votingSystem.services.ElectionService;
import com.example.votingSystem.services.PartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.Month;

@Component
@RequiredArgsConstructor
public class ExampleVotingProcess {

    private final ElectionService electionService;

    private final PartyService partyService;

    @PostConstruct
    private void init(){
        electionService.createElection(  "President Elections", LocalDateTime.of(2022, Month.MARCH,
                26, 0, 0, 0), LocalDateTime.of(2022, Month.MARCH,
                31, 0, 0, 0)
        );
        electionService.createElection( "Parliamentary Elections", LocalDateTime.of(2022, Month.MARCH,
                10, 0, 0, 0), LocalDateTime.of(2022, Month.MARCH,
                12, 0, 0, 0));
        electionService.createElection( "Past Elections", LocalDateTime.of(2022, Month.MARCH,
                26, 0, 0, 0), LocalDateTime.of(2022, Month.MARCH,
                31, 0, 0, 0));

        partyService.createParty("GERB", 1L);
        partyService.createParty("PP", 1L);
        partyService.createParty("BSP", 1L);
        partyService.createParty("DSP", 1L);
        partyService.createParty("ATAKA", 1L);

    }
}
