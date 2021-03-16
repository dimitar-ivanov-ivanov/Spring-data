package bookshop.system.repositories;

import java.util.List;
import bookshop.system.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> getAuthorsByFirstNameEndsWith(final String endsWith);

}
