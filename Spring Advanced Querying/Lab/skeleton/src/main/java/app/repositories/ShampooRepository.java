package app.repositories;

import java.math.BigDecimal;
import java.util.List;

import app.model.enums.Size;
import app.model.ingredients.Ingredient;
import app.model.labels.BasicLabel;
import app.model.labels.Label;
import app.model.shampoos.BasicShampoo;
import app.model.shampoos.Shampoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShampooRepository extends JpaRepository<BasicShampoo, Long> {

    List<Shampoo> findAllBySize(Size size);

    List<Shampoo> findAllBySizeOrLabelOrderByPriceAsc(final Size size, final BasicLabel label);

    List<Shampoo> findAllBySizeAndLabelId(Size size, long labelId);

    List<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(final BigDecimal price);

    long countAllByPriceLessThan(final BigDecimal price);

    @Query("SELECT s " +
            "FROM BasicShampoo AS s " +
            "JOIN s.ingredients AS i " +
            "WHERE i IN :ingredients")
    List<Shampoo> findByHavingIngredients(@Param("ingredients") List<Ingredient> ingredients);

    @Query("SELECT s " +
            "FROM BasicShampoo AS s " +
            "WHERE s.ingredients.size < :count")
    List<Shampoo> findByCountOfIngredientsLessThan(@Param("count") int count);

    @Query("SELECT sum(i.price) " +
            "FROM BasicShampoo AS s " +
            "JOIN s.ingredients AS i " +
            "WHERE s.brand = :brand"
    )
    BigDecimal getTotalIngredientCostForShampoo(@Param("brand") String brand);
}
