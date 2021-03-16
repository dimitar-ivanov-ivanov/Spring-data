package bookshop.system.runners;

import bookshop.system.enums.AgeRestriction;
import bookshop.system.enums.BookEdition;
import bookshop.system.models.Author;
import bookshop.system.models.Book;
import bookshop.system.models.Category;
import bookshop.system.services.interfaces.AuthorService;
import bookshop.system.services.interfaces.BookService;
import bookshop.system.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

@Configuration
public class Runner {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    private static final String ENCODING = "UTF8";
    private static final String RESOURCES_PATH = "src\\main\\resources\\";
    private static final String AUTHORS_FILE = "authors.txt";
    private static final String CATEGORIES_FILE = "categories.txt";
    private static final String BOOKS_FILE = "books.txt";
    private static final String INPUT_SEPARATOR = "\\s+";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("d/M/yyyy");
    private static final String NAME_DELIMITER = " ";

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            //getAllBooksReleasedAfter2000(bookService);
            //getAuthorsWithAtLeastOneBookBefore1990(authorService);
            //getAuthorsOrderedByBookCount(authorService);
            //getBooksByGeorgeOrwell(bookService);
            //getBooksByAgeRestriction();
            //getBooksByBookEditionAndCopies("Promo", 5000);
            //getBooksByPriceRange(5, 40);
            //getBooksWithReleaseDateYearNotEqualTo();
            //getBooksWithReleaseDateLessThan();
            //getAuthorsByFirstNameEndsWith();
            //getBooksWithTitleContaining();
            
            //seedAuthors(authorService);
            //seedCategories(categoryService);
            //seedBook(bookService, authorService, categoryService);
        };
    }

    private void getBooksWithTitleContaining() {
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();

        bookService.getBooksByTitleContaining(word)
                .forEach(System.out::println);
    }

    private void getAuthorsByFirstNameEndsWith() {
        Scanner scanner = new Scanner(System.in);
        String endsWith = scanner.nextLine();

        authorService.getAuthorsByFirstNameEndsWith(endsWith)
                .forEach(System.out::println);
    }

    private void getBooksWithReleaseDateLessThan() {
        Scanner scanner = new Scanner(System.in);
        String[] args = scanner.nextLine().split("-");
        int day = Integer.parseInt(args[0]);
        int month = Integer.parseInt(args[1]);
        int year = Integer.parseInt(args[2]);
        LocalDate date = LocalDate.of(year, month, day);

        bookService.getBooksByReleaseDateIsLessThan(date)
                .forEach(System.out::println);

    }

    private void getBooksWithReleaseDateYearNotEqualTo() {
        Scanner scanner = new Scanner(System.in);
        int year = Integer.parseInt(scanner.nextLine());

        bookService.getBooksByReleaseDateYearNotEqualTo(year)
                .forEach(System.out::println);
    }

    private void getBooksByPriceRange(int lower, int upper) {
        BigDecimal lowerLimit = new BigDecimal(lower);
        BigDecimal upperLimit = new BigDecimal(upper);

        bookService.findAllByPriceLowerThanAndHigherThan(lowerLimit, upperLimit)
                .forEach(System.out::println);
    }

    private void getBooksByBookEditionAndCopies(String name, long copies) {
        BookEdition bookEdition = BookEdition.valueOf(name.toUpperCase());
        bookService.findAllByBookEditionAndCopiesLessThan(bookEdition, copies)
                .forEach(System.out::println);
    }

    private void getBooksByAgeRestriction() {
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine().toUpperCase();
        AgeRestriction ageRestriction = AgeRestriction.valueOf(word);

        bookService.findAllByAgeRestriction(ageRestriction)
                .forEach(System.out::println);
    }

    private void getBooksByGeorgeOrwell(BookService bookService) {
        bookService.getAllBooksFromGeorgePowell()
                .forEach(System.out::println);
    }

    private void getAuthorsOrderedByBookCount(AuthorService authorService) {
        authorService.getAuthorsOrderedByNumberOfWrittenBooks()
                .forEach(System.out::println);
    }

    private void getAuthorsWithAtLeastOneBookBefore1990(AuthorService authorService) {
        authorService.getAuthorsWithAtLeastOneBookAfter1990()
                .forEach(System.out::println);
    }

    private void getAllBooksReleasedAfter2000(BookService bookService) {
        LocalDate date = LocalDate.of(2000, 12, 31);
        bookService.getTitlesOfAllBooksReleasedAfter(date)
                .forEach(System.out::println);
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
