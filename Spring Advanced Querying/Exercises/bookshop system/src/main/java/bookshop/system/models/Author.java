package bookshop.system.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "author")
@Table(name = "authors")
@NamedStoredProcedureQuery(
        name = "udp_find_books_by_author",
        procedureName = "udp_find_books_by_author",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "first_name", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "last_name", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "books_count", type = Integer.class)
        }
)
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
            name = "last_name",
            nullable = false
    )
    private String lastName;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private Set<Book> books;

    public Author() {
        this.books = new HashSet<>();
    }

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
