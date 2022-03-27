package com.example.votingSystem.models;

import lombok.*;
import org.hibernate.Hibernate;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "Elections")
public class Election {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "Begin")
    private LocalDateTime beginning;

    @Column(name = "Expired")
    private LocalDateTime end;

    @OneToMany(mappedBy = "currElection")
    @ToString.Exclude
    Set<Party> parties = new HashSet<>();

    @ManyToMany(mappedBy = "elections", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @ToString.Exclude
    Set<User> electionUsers = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Election election = (Election) o;
        return id != null && Objects.equals(id, election.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}



