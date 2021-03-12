package bookshop.system.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "category")
@Table(
        name = "categories",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "category_name_unique",
                        columnNames = "name"
                )
        }
)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "category_id",
            nullable = false
    )
    private long id;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Book> books;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
        this.books = new HashSet<>();
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

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
