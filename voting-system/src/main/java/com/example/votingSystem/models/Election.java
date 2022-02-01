package com.example.votingSystem.models;
import javax.persistence.*;

import java.util.*;


@Entity
@Table(name = "election")
public class Election {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "party_id")
    private List<Party> parties = new ArrayList<>();

    protected Election() {
    }

    public Election(String name) {
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

    public List<Party> getParties() {
        return parties;
    }

    public void setParties(List<Party> parties) {
        this.parties = parties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Election election = (Election) o;
        return getId().equals(election.getId()) && getName().equals(election.getName()) && getParties().equals(election.getParties());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getParties());
    }
}
