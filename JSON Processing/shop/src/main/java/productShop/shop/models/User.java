package productShop.shop.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false
    )
    @Size(min = 3)
    private String lastName;

    @Min(1)
    @Max(120)
    private int age;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private Set<User> friends;

    public User() {
        this.friends = new HashSet<>();
    }

    public User(String firstName, @Size(min = 3) String lastName, @Min(1) @Max(120) int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.friends = new HashSet<>();
    }
}
