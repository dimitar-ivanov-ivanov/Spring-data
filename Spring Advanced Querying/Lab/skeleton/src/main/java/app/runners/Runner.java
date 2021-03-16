package app.runners;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

import app.model.enums.Size;
import app.model.ingredients.Ingredient;
import app.model.shampoos.BasicShampoo;
import app.services.interfaces.IngredientService;
import app.services.interfaces.LabelService;
import app.services.interfaces.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Runner {

    @Autowired
    private ShampooService shampooService;

    @Autowired
    private LabelService labelService;

    @Autowired
    private IngredientService ingredientService;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {

            /*List<BasicShampoo> basicShampoos = shampooService.getAllShampoosBySizeOrderedById(Size.MEDIUM);
            basicShampoos.forEach(System.out::println);

            shampooService.findAllBySizeOrLabelOrderByPriceAsc(
                    Size.MEDIUM,
                    labelService.findById(10)
            ).forEach(System.out::println);

            shampooService.findAllByPriceGreaterThanOrderByPriceDesc(new BigDecimal("5"))
                    .forEach(System.out::println);*/

            /*List<Ingredient> ingredients = ingredientService
                    .findAllByNameIsStartingWith("M");

            ingredients.forEach(System.out::println);*/

            /*
            List<String> names = new ArrayList<>();
            names.add("Lavender");
            names.add("Herbs");
            names.add("Apple");
            ingredientService.findAllByNameInOrderByPriceAsc(names)
                    .forEach(System.out::println);*/

            /*long count = shampooService.countAllByPriceLessThan(
                    new BigDecimal("8.5")
            );

            System.out.println(count);*/

            /*
            List<Ingredient> ingredients = new ArrayList<>();

            ingredients.addAll(ingredientService.findAllByNameIsStartingWith("Mineral-Colagen"));
            ingredients.addAll(ingredientService.findAllByNameIsStartingWith("Berry"));
            shampooService.findByHavingIngredients(ingredients)
                    .forEach(System.out::println);*/

            /*
            shampooService.findByCountOfIngredientsLessThan(2)
                    .forEach(System.out::println);*/

            /*
            BigDecimal silCombTotalIngredientCost = shampooService.findByNameAndGetIngredientPrices("Silk Comb");
            BigDecimal freshItUpTotalIngredientCost = shampooService.findByNameAndGetIngredientPrices("Fresh it up!");

            System.out.println(silCombTotalIngredientCost);
            System.out.println(freshItUpTotalIngredientCost);*/

            this.ingredientService
                    .deleteIngredientByName("Active-Caffeine");
            System.out.println("Deleted Active-Caffeine");

            this.ingredientService
                    .deleteIngredientByNameNamedQuery("Micro-Crystals");
            System.out.println("Deleted Micro-Crystals");
        };
    }
}
