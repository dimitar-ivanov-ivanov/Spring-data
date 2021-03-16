package bookshop.system.mappingClasses;

import java.math.BigDecimal;

public class BookWithTitleAndPrice {

    private String title;

    private BigDecimal price;

    public BookWithTitleAndPrice(String title, BigDecimal price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BookWithTitleAndPrice{" +
                "title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
