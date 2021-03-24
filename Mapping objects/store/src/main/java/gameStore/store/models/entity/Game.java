package gameStore.store.models.entity;

import gameStore.store.annotations.url.URL;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
    @Pattern(regexp = "^[A-Z][a-zA-Z0-9 ]{2,99}$")
    private String title;

    @Column(
            name = "trailer"
    )
    @Pattern(regexp = "^https:\\/\\/www\\.youtube\\.com\\/watch\\?v=([a-zA-Z]{11})$")
    private String trailer;

    @Column(
            name = "thumbnail",
            nullable = false
    )
    @URL
    private String thumbnail;

    @Column(
            name = "price",
            precision = 20,
            scale = 2
    )
    private BigDecimal price;

    @Column(
            name = "size",
            precision = 10,
            scale = 1,
            columnDefinition = "DOUBLE(10,1) DEFAULT 0.0"
    )
    private double size;

    @Column(
            name = "description",
            columnDefinition = "TEXT"
    )
    @Size(min = 20)
    private String description;

    @Column(
            name = "release_date"
    )
    private LocalDate releaseDate;

    public Game() {
    }

    public Game(@Pattern(regexp = "^[A-Z][a-zA-Z0-9 ]{2,99}$") String title,
                @Pattern(regexp = "^https:\\/\\/www\\.youtube\\.com\\/watch\\?v=([a-zA-Z]{11})$") String trailer,
                String thumbnail,
                BigDecimal price,
                double size,
                @Size(min = 20) String description,
                LocalDate releaseDate) {
        this.title = title;
        this.trailer = trailer;
        this.thumbnail = thumbnail;
        this.price = price;
        this.size = size;
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
