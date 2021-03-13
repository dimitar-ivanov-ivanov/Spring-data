package bookshop.system.services.interfaces;

import bookshop.system.models.Book;
import bookshop.system.services.BookWithReleaseDateAndCopies;

import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void registerBook(Book book);

    List<String> getTitlesOfAllBooksReleasedAfter(LocalDate date);

    List<BookWithReleaseDateAndCopies> getAllBooksFromGeorgePowell();
}
