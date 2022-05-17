package com.example.votingSystem.models;

import lombok.*;
import org.hibernate.Hibernate;
import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "Votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "HashedPIN", nullable = false)
    private String hashed_PIN;

    @ManyToOne
    @JoinColumn(name = "party_id", updatable = false)
    private Party votedParty;

    @ManyToOne
    @JoinColumn(name = "candidate_id", updatable = false)
    private Candidate votedCandidate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Vote vote = (Vote) o;
        return id != null && Objects.equals(id, vote.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
