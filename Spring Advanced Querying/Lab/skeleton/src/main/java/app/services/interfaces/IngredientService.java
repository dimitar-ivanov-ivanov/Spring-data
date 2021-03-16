package app.services.interfaces;

import java.util.Collection;
import java.util.List;
import app.model.ingredients.Ingredient;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface IngredientService {
    List<Ingredient> findAllByNameIsStartingWith(final String name);

    List<Ingredient> findAllByNameInOrderByPriceAsc(Collection<String> names);

    void deleteIngredientByName(@Param("name") String name);

    void deleteIngredientByNameNamedQuery(@Param("name") String name);
}
