package bookshop.system.runners;

import bookshop.system.enums.AgeRestriction;
import bookshop.system.enums.BookEdition;
import bookshop.system.models.Author;
import bookshop.system.models.Book;
import bookshop.system.models.Category;
import bookshop.system.services.interfaces.AuthorService;
import bookshop.system.services.interfaces.BookService;
import bookshop.system.services.interfaces.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;

@Configuration
public class Runner {

    private static final String ENCODING = "UTF8";
    private static final String RESOURCES_PATH = "src\\main\\resources\\";
    private static final String AUTHORS_FILE = "authors.txt";
    private static final String CATEGORIES_FILE = "categories.txt";
    private static final String BOOKS_FILE = "books.txt";
    private static final String INPUT_SEPARATOR = "\\s+";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("d/M/yyyy");
    private static final String NAME_DELIMITER = " ";

    @Bean
    CommandLineRunner commandLineRunner
            (BookService bookService, AuthorService authorService, CategoryService categoryService) {
        return args -> {
            seedAuthors(authorService);
            seedCategories(categoryService);
            seedBook(bookService, authorService, categoryService);
        };
    }

    private void seedBook(BookService bookService, AuthorService authorService, CategoryService categoryService) {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(RESOURCES_PATH + BOOKS_FILE), ENCODING))) {

            reader.lines()
                    .map(line -> line.split(INPUT_SEPARATOR))
                    .forEach(line -> {
                        Book book = new Book();
                        book.setAuthor(authorService.getRandomAuthor());
                        book.setEdition(BookEdition.values()[1]);

                        int[] dateArgs = Arrays
                                .stream(line[1].split("/"))
                                .mapToInt(e -> Integer.parseInt(e))
                                .toArray();

                        LocalDate date = LocalDate.of(dateArgs[2], dateArgs[1], dateArgs[0]);
                        book.setReleaseDate(date);

                        book.setCopies(Integer.parseInt(line[2]));
                        book.setPrice(new BigDecimal(line[3]));

                        book.setAgeRestriction(AgeRestriction.values()[1]);
                        book.setTitle(String.join(NAME_DELIMITER, Arrays.copyOfRange(line, 5, line.length)));
                        book.setCategories(categoryService.getRandomCategories());

                        bookService.registerBook(book);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void seedCategories(CategoryService categoryService) {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(RESOURCES_PATH + CATEGORIES_FILE), ENCODING))) {
            reader.lines()
                    .map(line -> line.split(INPUT_SEPARATOR))
                    .forEach(name -> {
                        if (!name[0].trim().isEmpty()) {
                            Category category = new Category(name[0]);
                            categoryService.registerCategory(category);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void seedAuthors(AuthorService authorService) {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(RESOURCES_PATH + AUTHORS_FILE), ENCODING))) {
            reader.lines()
                    .map(line -> line.split(INPUT_SEPARATOR))
                    .forEach(names -> {
                        Author author = new Author();
                        author.setFirstName(names[0]);
                        author.setLastName(names[1]);
                        authorService.registerAuthor(author);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
