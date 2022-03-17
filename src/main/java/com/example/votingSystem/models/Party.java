package com.example.votingSystem.models;

import lombok.*;
import org.hibernate.Hibernate;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "Parties")
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "election_id", insertable = false, updatable = false)
    private Election currElection;

    @OneToMany(mappedBy = "currParty")
    @ToString.Exclude
    Set<Candidate> partyPeople = new HashSet<>();

    @OneToMany(mappedBy = "votedParty")
    @ToString.Exclude
    Set<Vote> partyVotes = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Party party = (Party) o;
        return id != null && Objects.equals(id, party.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

