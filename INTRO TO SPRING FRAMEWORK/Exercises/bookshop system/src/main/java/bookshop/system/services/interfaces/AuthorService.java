package bookshop.system.services.interfaces;

import bookshop.system.models.Author;
import bookshop.system.services.AuthorWithBookCount;
import bookshop.system.services.BookWithReleaseDateAndCopies;

import java.util.List;

public interface AuthorService {
    void registerAuthor(Author author);

    Author getRandomAuthor();

    List<String> getAuthorsWithAtLeastOneBookAfter1990();

    List<AuthorWithBookCount> getAuthorsOrderedByNumberOfWrittenBooks();
}
