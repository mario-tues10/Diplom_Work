package com.example.votingSystem.models;


import javax.persistence.*;

@Entity
@Table(name = "voters")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "unique_personal_number", nullable = false)
    private String PIN;

    public User() {
    }

    public User(Long id, String PIN) {
        this.id = id;
        this.PIN = PIN;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
