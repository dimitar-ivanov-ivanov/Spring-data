package com.company.entities;

import javax.persistence.*;

@Entity
@Table(name = "competitions")
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "competition_type_id")
    private CompetitionType competitionType;

    public Competition(){}

    public Competition(String name, CompetitionType competitionType) {
        this.name = name;
        this.competitionType = competitionType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CompetitionType getCompetitionType() {
        return competitionType;
    }

    public void setCompetitionType(CompetitionType competitionType) {
        this.competitionType = competitionType;
    }
}
