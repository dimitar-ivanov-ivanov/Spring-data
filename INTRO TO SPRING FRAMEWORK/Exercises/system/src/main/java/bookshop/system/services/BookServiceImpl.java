package bookshop.system.services;

import bookshop.system.models.Book;
import bookshop.system.repositories.BookRepository;
import bookshop.system.services.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
