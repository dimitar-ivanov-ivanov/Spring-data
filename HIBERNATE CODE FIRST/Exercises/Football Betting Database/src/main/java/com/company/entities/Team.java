package com.company.entities;

import javax.persistence.*;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "logo")
    private byte[] logo;

    @Column(name = "initials", length = 3)
    private String initials;

    @OneToOne
    private Color primaryKitColor;

    @OneToOne
    private Color secondaryKitColor;

    @ManyToOne
    private Town town;

    private long budget;

    public Team(String name, byte[] logo, String initials, Color primaryKitColor, Color secondaryKitColor, Town town, long budget) {
        this.name = name;
        this.logo = logo;
        this.initials = initials;
        this.primaryKitColor = primaryKitColor;
        this.secondaryKitColor = secondaryKitColor;
        this.town = town;
        this.budget = budget;
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

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public Color getPrimaryKitColor() {
        return primaryKitColor;
    }

    public void setPrimaryKitColor(Color primaryKitColor) {
        this.primaryKitColor = primaryKitColor;
    }

    public Color getSecondaryKitColor() {
        return secondaryKitColor;
    }

    public void setSecondaryKitColor(Color secondaryKitColor) {
        this.secondaryKitColor = secondaryKitColor;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }
}
