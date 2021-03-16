package bookshop.system.mappingClasses;

import bookshop.system.enums.BookEdition;

import java.math.BigDecimal;

public class BookWIthTitlePriceAndEditionType {

    private String title;

    private BigDecimal price;

    private BookEdition bookEdition;

    public BookWIthTitlePriceAndEditionType(String title, BigDecimal price, BookEdition bookEdition) {
        this.title = title;
        this.price = price;
        this.bookEdition = bookEdition;
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

    public BookEdition getBookEdition() {
        return bookEdition;
    }

    public void setBookEdition(BookEdition bookEdition) {
        this.bookEdition = bookEdition;
    }

    @Override
    public String toString() {
        return "BookWIthTitlePriceAndEditionType{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", bookEdition=" + bookEdition +
                '}';
    }
}
