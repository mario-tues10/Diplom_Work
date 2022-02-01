package com.example.votingSystem.models;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "parties")

public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "candidate_id")
    private List<Candidate> participants = new ArrayList<>();

    protected Party() {
    }

    public Party(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Candidate> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Candidate> participants) {
        this.participants = participants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Party party = (Party) o;
        return getId().equals(party.getId()) && getName().equals(party.getName()) && getParticipants().equals(party.getParticipants());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getParticipants());
    }
}
