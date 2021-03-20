package mapping.lab.entities;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(
            name = "town",
            nullable = false
    )
    private String town;

    @Column(
            name = "street",
            nullable = false
    )
    private String street;

    @Column(
            name = "number"
    )
    private int number;

    public Address() {
    }

    public Address(String town, String street, int number) {
        this.town = town;
        this.street = street;
        this.number = number;
    }
}
