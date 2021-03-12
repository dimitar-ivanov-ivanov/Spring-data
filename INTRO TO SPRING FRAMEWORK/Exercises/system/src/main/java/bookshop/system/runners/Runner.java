package bookshop.system.runners;

import bookshop.system.models.Author;
import bookshop.system.repositories.AuthorRepository;
import bookshop.system.enums.AgeRestriction;
import bookshop.system.models.Book;
import bookshop.system.enums.BookEdition;
import bookshop.system.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
public class Runner {

    @Bean
    CommandLineRunner commandLineRunner
            (BookRepository bookRepository, AuthorRepository authorRepository) {
        return args -> {

            Author dimitar = new Author(
                    "Dimitar",
                    "Ivanov"
            );

            Book book1 = new Book(
                    "Crime and Punishment",
                    "Description1",
                    BookEdition.PROMO,
                    new BigDecimal("13.56"),
                    2020202,
                    LocalDate.of(1865, 3, 15),
                    AgeRestriction.TEEN
            );

            Book book2 = new Book(
                    "Title1",
                    "Description1",
                    BookEdition.PROMO,
                    new BigDecimal("23.56"),
                    2,
                    LocalDate.of(1995, 3, 15),
                    AgeRestriction.TEEN
            );

            authorRepository.save(dimitar);

            book1.setAuthor(dimitar);
            book2.setAuthor(dimitar);
            bookRepository.saveAll(List.of(book1, book2));
        };
    }
}
