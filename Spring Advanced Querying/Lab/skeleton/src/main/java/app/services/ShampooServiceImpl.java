package app.services;

import app.model.enums.Size;
import app.model.ingredients.Ingredient;
import app.model.labels.BasicLabel;
import app.model.shampoos.Shampoo;
import app.repositories.ShampooRepository;
import app.services.interfaces.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ShampooServiceImpl implements ShampooService {

    private ShampooRepository shampooRepository;

    @Autowired
    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<Shampoo> getAllShampoosBySizeOrderedById(Size size) {
        List<Shampoo> basicShampoos = this.shampooRepository
                .findAllBySize(size);

        Collections.sort(basicShampoos, Comparator.comparingLong(value -> value.getId()));
        return basicShampoos;
    }

    @Override
    public List<Shampoo> findAllBySizeOrLabelOrderByPriceAsc(Size size, BasicLabel label) {
        return this.shampooRepository
                .findAllBySizeOrLabelOrderByPriceAsc(size, label);
    }

    @Override
    public List<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price) {
        return this.shampooRepository
                .findAllByPriceGreaterThanOrderByPriceDesc(price);
    }

    @Override
    public List<Shampoo> findByHavingIngredients(List<Ingredient> ingredients) {
        return this.shampooRepository
                .findByHavingIngredients(ingredients);
    }

    @Override
    public long countAllByPriceLessThan(BigDecimal price) {
        return this.shampooRepository
                .countAllByPriceLessThan(price);
    }

    @Override
    public List<Shampoo> findByCountOfIngredientsLessThan(int count) {
        return this.shampooRepository
                .findByCountOfIngredientsLessThan(count);
    }

    @Override
    public BigDecimal findByNameAndGetIngredientPrices(String brand) {
        return this.shampooRepository
                .getTotalIngredientCostForShampoo(brand);
    }
}
