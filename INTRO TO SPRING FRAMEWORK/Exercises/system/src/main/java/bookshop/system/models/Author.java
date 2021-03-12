package bookshop.system.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "author")
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "author_id",
            nullable = false
    )
    private long id;

    @Column(
            name = "first_name",
            nullable = true
    )
    private String firstName;

    @Column(
            name = "second_name",
            nullable = false
    )
    private String secondName;

    @OneToMany(mappedBy = "author")
    private Set<Book> books;

    public Author() {
        this.books = new HashSet<>();
    }

    public Author(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.books = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
}
