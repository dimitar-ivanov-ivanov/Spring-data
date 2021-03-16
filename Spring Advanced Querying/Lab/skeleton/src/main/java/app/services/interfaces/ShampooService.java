package app.services.interfaces;

import java.math.BigDecimal;
import java.util.List;

import app.model.enums.Size;
import app.model.ingredients.Ingredient;
import app.model.labels.BasicLabel;
import app.model.labels.Label;
import app.model.shampoos.BasicShampoo;
import app.model.shampoos.Shampoo;
import org.springframework.data.repository.query.Param;

public interface ShampooService {

    List<Shampoo> getAllShampoosBySizeOrderedById(Size size);

    List<Shampoo> findAllBySizeOrLabelOrderByPriceAsc(final Size size, final BasicLabel label);

    List<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(final BigDecimal price);

    List<Shampoo> findByHavingIngredients(@Param("ingredients") List<Ingredient> ingredients);

    long countAllByPriceLessThan(final BigDecimal price);

    List<Shampoo> findByCountOfIngredientsLessThan(@Param("count") int count);

    BigDecimal findByNameAndGetIngredientPrices(@Param("brand") String brand);
}
