package bookshop.system.services;

import bookshop.system.models.Author;
import bookshop.system.repositories.AuthorRepository;
import bookshop.system.services.interfaces.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

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
}
