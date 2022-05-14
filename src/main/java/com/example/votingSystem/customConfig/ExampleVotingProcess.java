package com.example.votingSystem.customConfig;

import com.example.votingSystem.services.CandidateService;
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

    private final CandidateService candidateService;

    @PostConstruct
    private void init(){
        electionService.createElection(  "President Elections", LocalDateTime.of(2022, Month.MAY,
                11, 0, 0, 0), LocalDateTime.of(2022, Month.MAY,
                31, 0, 0, 0)
        );
        electionService.createElection( "Parliamentary Elections", LocalDateTime.of(2022, Month.MARCH,
                10, 0, 0, 0), LocalDateTime.of(2022, Month.MARCH,
                12, 0, 0, 0));
        electionService.createElection( "Past Elections", LocalDateTime.of(2022, Month.MAY,
                11, 0, 0, 0), LocalDateTime.of(2022, Month.MAY,
                31, 0, 0, 0));

        partyService.createParty("GERB", 1L);
        partyService.createParty("PP", 1L);
        partyService.createParty("BSP", 1L);
        partyService.createParty("DSP", 1L);
        partyService.createParty("ATAKA", 1L);

        candidateService.createCandidate("Teodor Atanasov", 1L);
        candidateService.createCandidate("Ivan Andreev", 1L);
        candidateService.createCandidate("Kaloyan Stefanov", 1L);
        candidateService.createCandidate("Teodor Atanasov", 2L);
        candidateService.createCandidate("Teodor Atanasov", 2L);
        candidateService.createCandidate("Teodor Atanasov", 2L);
        candidateService.createCandidate("Teodor Atanasov", 3L);
        candidateService.createCandidate("Teodor Atanasov", 3L);
        candidateService.createCandidate("Teodor Atanasov", 3L);
        candidateService.createCandidate("Teodor Atanasov", 4L);
        candidateService.createCandidate("Teodor Atanasov", 4L);
        candidateService.createCandidate("Teodor Atanasov", 4L);
        candidateService.createCandidate("Teodor Atanasov", 5L);
        candidateService.createCandidate("Teodor Atanasov", 5L);
        candidateService.createCandidate("Teodor Atanasov", 5L);
    }
}
