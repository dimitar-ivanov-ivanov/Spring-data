package bookshop.system.models;

import bookshop.system.enums.AgeRestriction;
import bookshop.system.enums.BookEdition;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "book")
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "book_id",
            nullable = false
    )
    private long id;

    @Column(
            name = "title",
            length = 50,
            nullable = false
    )
    private String title;

    @Column(
            name = "description",
            length = 1000,
            nullable = true
    )
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "edition_type", nullable = false)
    private BookEdition edition;

    @Column(
            name = "price",
            nullable = false
    )
    private BigDecimal price;

    @Column(
            name = "copies",
            nullable = false
    )
    private Long copies;

    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "age_restriction", nullable = false)
    private AgeRestriction ageRestriction;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "author_id", referencedColumnName = "author_id")
    private Author author;

    public Book() {
    }

    public Book
            (String title, String description, BookEdition edition, BigDecimal price, long copies,
             LocalDate releaseDate, AgeRestriction ageRestriction) {
        this.title = title;
        this.description = description;
        this.edition = edition;
        this.price = price;
        this.copies = copies;
        this.releaseDate = releaseDate;
        this.ageRestriction = ageRestriction;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BookEdition getEdition() {
        return edition;
    }

    public void setEdition(BookEdition edition) {
        this.edition = edition;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getCopies() {
        return copies;
    }

    public void setCopies(long copies) {
        this.copies = copies;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public void setCopies(Long copies) {
        this.copies = copies;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
