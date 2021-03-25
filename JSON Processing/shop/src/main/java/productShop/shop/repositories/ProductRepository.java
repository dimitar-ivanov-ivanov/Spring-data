package productShop.shop.repositories;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import productShop.shop.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAllByPriceBetweenOrderByPrice(BigDecimal lower, BigDecimal upper);
}
