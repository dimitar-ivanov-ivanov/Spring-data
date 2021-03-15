package User.System.system.models;

import User.System.system.constants.TextConstants;
import jdk.jfr.Enabled;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "town")
@Table(name = "towns")
public class Town {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "town_id",
            nullable = false
    )
    private long id;

    @Column(
            name = "name",
            nullable = false
    )
    @Size(min = 1, max = 50, message = TextConstants.TOWN_NAME_INCORRECT)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "country_id", referencedColumnName = "country_id")
    private Country country;

    public Town() {
    }

    public Town(@Size(min = 1, max = 50, message = TextConstants.TOWN_NAME_INCORRECT) String name, Country country) {
        this.name = name;
        this.country = country;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
