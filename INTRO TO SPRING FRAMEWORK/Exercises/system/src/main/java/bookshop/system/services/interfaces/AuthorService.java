package bookshop.system.services.interfaces;

import bookshop.system.models.Author;

public interface AuthorService {
    void registerAuthor(Author author);

    Author getRandomAuthor();
}
