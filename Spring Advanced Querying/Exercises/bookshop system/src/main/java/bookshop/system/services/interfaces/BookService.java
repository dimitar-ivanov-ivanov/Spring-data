package bookshop.system.services.interfaces;

import bookshop.system.enums.AgeRestriction;
import bookshop.system.enums.BookEdition;
import bookshop.system.mappingClasses.BookWIthTitlePriceAndEditionType;
import bookshop.system.mappingClasses.BookWithTitleAndPrice;
import bookshop.system.models.Book;
import bookshop.system.mappingClasses.BookWithReleaseDateAndCopies;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void registerBook(Book book);

    List<String> getTitlesOfAllBooksReleasedAfter(LocalDate date);

    List<BookWithReleaseDateAndCopies> getAllBooksFromGeorgePowell();

    List<String> findAllByAgeRestriction(final AgeRestriction ageRestriction);

    List<String> findAllByBookEditionAndCopiesLessThan(final BookEdition bookEdition, final Long copies);

    List<BookWithTitleAndPrice> findAllByPriceLowerThanAndHigherThan(final BigDecimal lowerLimit, final BigDecimal upperLimit);

    List<String> getBooksByReleaseDateYearNotEqualTo(@Param("year") final int year);

    List<BookWIthTitlePriceAndEditionType> getBooksByReleaseDateIsLessThan(final LocalDate date);

    List<String> getBooksByTitleContaining(final String str);
}
