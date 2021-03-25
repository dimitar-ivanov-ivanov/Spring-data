package productShop.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import productShop.shop.models.Category;
import productShop.shop.models.User;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query(value = "SELECT * FROM categories ORDER BY RAND () LIMIT 1", nativeQuery = true)
    Category getRandomEntity();
}
