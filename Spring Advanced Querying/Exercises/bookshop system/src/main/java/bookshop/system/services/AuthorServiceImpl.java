package bookshop.system.services;

import bookshop.system.mappingClasses.AuthorWithBookCount;
import bookshop.system.models.Author;
import bookshop.system.repositories.AuthorRepository;
import bookshop.system.services.interfaces.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void registerAuthor(Author author) {
        this.authorRepository.save(author);
    }

    @Override
    public Author getRandomAuthor() {
        List<Author> authors = authorRepository.findAll();
        int random = (int) (Math.random() * (authors.size()));
        return authors.get(random);
    }

    @Override
    public List<String> getAuthorsWithAtLeastOneBookAfter1990() {
        return authorRepository
                .findAll()
                .stream()
                .filter(author ->
                        author.getBooks()
                                .stream()
                                .filter(book -> book.getReleaseDate().getYear() < 1990)
                                .collect(Collectors.toList())
                                .size() >= 1
                )
                .map(author -> author.getFirstName() + " " + author.getLastName())
                .collect(Collectors.toList());
    }

    @Override
    public List<AuthorWithBookCount> getAuthorsOrderedByNumberOfWrittenBooks() {
        return authorRepository
                .findAll()
                .stream()
                .map(author -> new AuthorWithBookCount(
                        author.getFirstName(),
                        author.getLastName(),
                        author.getBooks().size()
                ))
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAuthorsByFirstNameEndsWith(String endsWith) {
        return this.authorRepository
                .getAuthorsByFirstNameEndsWith(endsWith)
                .stream()
                .map(author -> {
                    return author.getFirstName() + " " + author.getLastName();
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getTotalNumberOfBookCopiesByAuthor() {
        return this.authorRepository
                .getTotalNumberOfBookCopiesByAuthor();
    }
}
