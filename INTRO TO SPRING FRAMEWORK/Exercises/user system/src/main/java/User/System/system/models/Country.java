package User.System.system.models;

import User.System.system.constants.TextConstants;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "country")
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "country_id",
            nullable = false
    )
    private long id;

    @Column(
            name = "name",
            nullable = false
    )
    @Size(min = 1, max = 50, message = TextConstants.COUNTRY_NAME_INCORRECT)
    private String name;

    @OneToMany(mappedBy = "country")
    private Set<Town> towns;

    public Country() {
    }

    public Country(@Size(min = 1, max = 50, message = TextConstants.COUNTRY_NAME_INCORRECT) String name) {
        this.name = name;
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
}
