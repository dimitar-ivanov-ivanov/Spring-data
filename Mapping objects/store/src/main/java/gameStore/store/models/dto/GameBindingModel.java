package gameStore.store.models.dto;

import gameStore.store.annotations.url.URL;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class GameBindingModel {

    private String title;

    @Pattern(regexp = "^https:\\/\\/www\\.youtube\\.com\\/watch\\?v=([a-zA-Z]{11})$")
    private String trailer;
    private String thumbnail;
    private BigDecimal price;
    private double size;
    private String description;

    public GameBindingModel() {
    }

    public GameBindingModel(String title,
                            @Pattern(regexp = "^https:\\/\\/www\\.youtube\\.com\\/watch\\?v=([a-zA-Z]{11})$") String trailer,
                            String thumbnail,
                            BigDecimal price,
                            double size,
                            String description) {
        this.title = title;
        this.trailer = trailer;
        this.thumbnail = thumbnail;
        this.price = price;
        this.size = size;
        this.description = description;
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

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
