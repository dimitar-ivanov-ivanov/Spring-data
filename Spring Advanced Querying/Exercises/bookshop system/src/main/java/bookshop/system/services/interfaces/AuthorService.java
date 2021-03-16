package bookshop.system.services.interfaces;

import bookshop.system.models.Author;
import bookshop.system.mappingClasses.AuthorWithBookCount;

import java.util.List;

public interface AuthorService {
    void registerAuthor(Author author);

    Author getRandomAuthor();

    List<String> getAuthorsWithAtLeastOneBookAfter1990();

    List<AuthorWithBookCount> getAuthorsOrderedByNumberOfWrittenBooks();

    List<String> getAuthorsByFirstNameEndsWith(final String endsWith);
}
