package bookshop.system.repositories;

import java.util.List;

import bookshop.system.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> getAuthorsByFirstNameEndsWith(final String endsWith);

    @Query("SELECT CONCAT(a.firstName, ' ',a.lastName,' - ',SUM(b.copies)) FROM author AS a " +
            "JOIN a.books AS b " +
            "GROUP BY a.id " +
            "ORDER BY SUM(b.copies) DESC")
    List<String> getTotalNumberOfBookCopiesByAuthor();

    @Procedure(name = "udp_find_books_by_author")
    Integer getAuthorBooksCount(@Param("first_name") String first_name, @Param("last_name") String last_name);
}
