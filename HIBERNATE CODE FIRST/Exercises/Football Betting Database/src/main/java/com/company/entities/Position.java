package com.company.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "positions")
public class Position {

    @Id
    @Column(name = "id", length = 2, unique = true, nullable = false)
    private String id;

    @Column(name = "position_description", length = 30)
    private String positionDescription;

    public Position(){}

    public Position(String id, String positionDescription) {
        this.id = id;
        this.positionDescription = positionDescription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPositionDescription() {
        return positionDescription;
    }

    public void setPositionDescription(String positionDescription) {
        this.positionDescription = positionDescription;
    }
}
