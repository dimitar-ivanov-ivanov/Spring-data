package com.company.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @Column(name = "id", length = 3, unique = true, nullable = false)
    private String id;

    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name = "countries_continents",
            joinColumns = @JoinColumn(name = "country_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "continent_id", referencedColumnName = "id"))
    private Set<Continent> continents;

    public Country() {
        continents = new HashSet<>();
    }

    public Country(String id, String name) {
        this.id = id;
        this.name = name;
        continents = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Continent> getContinents() {
        return continents;
    }
}
