package gameStore.store.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "game_id",
            nullable = false
    )
    private long id;

    @Column(
            name = "title",
            nullable = false
    )
    private String title;

    @Column(
            name = "trailer"
    )
    private String trailer;

    @Column(
            name = "thumbnail",
            nullable = false
    )
    private String thumbnail;

    @Column(
            name = "price"
    )
    private BigDecimal price;

    @Column(
            name = "description"
    )
    private String description;

    @Column(
            name = "release_date"
    )
    private LocalDate releaseDate;

    public Game() {
    }

    public Game(String title, String trailer,
                String thumbnail, BigDecimal price,
                String description, LocalDate releaseDate) {
        this.title = title;
        this.trailer = trailer;
        this.thumbnail = thumbnail;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
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

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
