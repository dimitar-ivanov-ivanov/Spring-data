package bookshop.system.repositories;

import bookshop.system.enums.AgeRestriction;
import bookshop.system.enums.BookEdition;
import bookshop.system.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> getBooksByReleaseDateAfter(LocalDate date);

    List<Book> getBooksByAgeRestriction(final AgeRestriction ageRestriction);

    List<Book> getBooksByEditionAndCopiesLessThan(final BookEdition bookEdition, final Long copies);

    List<Book> getBooksByPriceIsLessThanOrPriceIsGreaterThan(final BigDecimal lowerLimit, final BigDecimal upperLimit);

    @Query("SELECT b FROM book AS b " +
            "WHERE YEAR(b.releaseDate) != :year")
    List<Book> getBooksByReleaseDateYearNotEqualTo(@Param("year") final int year);

    List<Book> getBooksByReleaseDateIsLessThan(final LocalDate date);

    List<Book> getBooksByTitleContaining(final String str);

    @Query("SELECT b FROM book AS b " +
            "WHERE b.author.lastName LIKE :start%")
    List<Book> getBooksByAuthorWhoseLastNameStartsWith(@Param("start") final String str);

    @Query("SELECT COUNT(b) FROM book AS b " +
            "WHERE LENGTH(b.title) > :length ")
    int getCountBooksWhoseTitleIsLongerThan(@Param("length") final int count);
}
