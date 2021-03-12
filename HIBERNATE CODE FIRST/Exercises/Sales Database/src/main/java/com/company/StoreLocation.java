package com.company;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "store_location")
public class StoreLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "location_name", nullable = false, unique = false)
    private String locationName;

    @OneToMany(mappedBy = "product")
    private Set<Sale> sales;

    public StoreLocation() {
        sales = new HashSet<>();
    }

    public StoreLocation(String locationName) {
        this.locationName = locationName;
        sales = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
