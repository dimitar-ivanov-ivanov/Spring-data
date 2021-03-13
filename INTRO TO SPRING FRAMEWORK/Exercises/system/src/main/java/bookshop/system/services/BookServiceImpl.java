package bookshop.system.services;

import bookshop.system.models.Author;
import bookshop.system.models.Book;
import bookshop.system.repositories.BookRepository;
import bookshop.system.services.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
