package app.services;

import app.model.ingredients.Ingredient;
import app.repositories.IngredientRepository;
import app.services.interfaces.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> findAllByNameIsStartingWith(String name) {
        return this.ingredientRepository
                .findAllByNameIsStartingWith(name);
    }

    @Override
    public List<Ingredient> findAllByNameInOrderByPriceAsc(Collection<String> names) {
        return this.ingredientRepository
                .findAllByNameInOrderByPriceAsc(names);
    }

    @Override
    public void deleteIngredientByName(String name) {
        this.ingredientRepository
                .deleteIngredientByName(name);
    }

    @Override
    public void deleteIngredientByNameNamedQuery(String name) {
        this.ingredientRepository
                .deleteIngredientByNameNamedQuery(name);
    }
}
