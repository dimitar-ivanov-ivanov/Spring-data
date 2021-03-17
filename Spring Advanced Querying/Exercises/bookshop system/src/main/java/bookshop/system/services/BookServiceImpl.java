package bookshop.system.services;

import bookshop.system.enums.AgeRestriction;
import bookshop.system.enums.BookEdition;
import bookshop.system.mappingClasses.BookWIthTitlePriceAndEditionType;
import bookshop.system.mappingClasses.BookWithReleaseDateAndCopies;
import bookshop.system.mappingClasses.BookWithTitleAndPrice;
import bookshop.system.models.Author;
import bookshop.system.models.Book;
import bookshop.system.repositories.BookRepository;
import bookshop.system.services.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void registerBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public List<String> getTitlesOfAllBooksReleasedAfter(LocalDate date) {
        return this.bookRepository
                .getBooksByReleaseDateAfter(date)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookWithReleaseDateAndCopies> getAllBooksFromGeorgePowell() {
        return this.bookRepository
                .findAll()
                .stream()
                .filter(book -> {
                    Author author = book.getAuthor();
                    return author.getFirstName().equals("George") &&
                            author.getLastName().equals("Powell");
                })
                .map(book -> new BookWithReleaseDateAndCopies(
                        book.getTitle(),
                        book.getReleaseDate(),
                        book.getCopies()
                ))
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllByAgeRestriction(final AgeRestriction ageRestriction) {
        return this.bookRepository
                .getBooksByAgeRestriction(ageRestriction)
                .stream()
                .map(book -> book.getTitle())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllByBookEditionAndCopiesLessThan(BookEdition bookEdition, Long copies) {
        return this.bookRepository
                .getBooksByEditionAndCopiesLessThan(bookEdition, copies)
                .stream()
                .map(book -> book.getTitle())
                .collect(Collectors.toList());
    }

    @Override
    public List<BookWithTitleAndPrice> findAllByPriceLowerThanAndHigherThan(BigDecimal lowerLimit, BigDecimal upperLimit) {
        return this.bookRepository
                .getBooksByPriceIsLessThanOrPriceIsGreaterThan(lowerLimit, upperLimit)
                .stream()
                .map(book -> new BookWithTitleAndPrice(book.getTitle(), book.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getBooksByReleaseDateYearNotEqualTo(int year) {
        return this.bookRepository
                .getBooksByReleaseDateYearNotEqualTo(year)
                .stream()
                .map(book -> book.getTitle())
                .collect(Collectors.toList());
    }

    @Override
    public List<BookWIthTitlePriceAndEditionType> getBooksByReleaseDateIsLessThan(LocalDate date) {
        return this.bookRepository
                .getBooksByReleaseDateIsLessThan(date)
                .stream()
                .map(book -> new BookWIthTitlePriceAndEditionType(
                        book.getTitle(),
                        book.getPrice(),
                        book.getEdition()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getBooksByTitleContaining(String str) {
        return this.bookRepository
                .getBooksByTitleContaining(str)
                .stream()
                .map(book -> book.getTitle())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getBooksByAuthorWhoseLastNameStartsWith(String str) {
        return this.bookRepository
                .getBooksByAuthorWhoseLastNameStartsWith(str)
                .stream()
                .map(book -> book.getTitle())
                .collect(Collectors.toList());
    }

    @Override
    public int countBooksWhoseTitleIsLongerThan(int length) {
        return this.bookRepository
                .getCountBooksWhoseTitleIsLongerThan(length);
    }

    @Override
    public int increaseBookCopies(long count, LocalDate date) {
        return this.bookRepository
                .increaseBookCopies(count, date);
    }

    @Override
    public int deleteBooksWhoseCopiesAreLessThan(long count) {
        return this.bookRepository
                .deleteBooksWhoseCopiesAreLessThan(count);
    }
}
