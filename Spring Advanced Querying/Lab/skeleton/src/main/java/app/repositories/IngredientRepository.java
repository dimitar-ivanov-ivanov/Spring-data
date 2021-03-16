package app.repositories;

import app.model.enums.Size;
import app.model.ingredients.BasicIngredient;
import app.model.ingredients.Ingredient;
import app.model.shampoos.Shampoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<BasicIngredient,Long> {

    List<Ingredient> findAllByNameIsStartingWith(final String name);

    List<Ingredient> findAllByNameInOrderByPriceAsc(Collection<String> names);

    @Modifying
    @Transactional
    @Query("DELETE FROM BasicIngredient AS b WHERE b.name = :name")
    void deleteIngredientByName(@Param("name") String name);

    @Modifying
    @Transactional
    void deleteIngredientByNameNamedQuery(@Param("name") String name);
}
