package productShop.shop.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import productShop.shop.models.dto.CategoryDto;
import productShop.shop.models.dto.ProductDto;
import productShop.shop.services.interfaces.CategoryService;
import productShop.shop.services.interfaces.ProductService;
import productShop.shop.utils.JsonParser;
import productShop.shop.models.dto.UserDto;
import productShop.shop.services.interfaces.UserService;

@Configuration
public class Runner {

    private static final String RESOURCES_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\";
    private static final String SEED_USERS_JSON = RESOURCES_PATH + "json\\users.json";
    private static final String SEED_CATEGORIES_JSON = RESOURCES_PATH + "json\\categories.json";
    private static final String SEED_PRODUCTS_JSON = RESOURCES_PATH + "json\\products.json";


    @Autowired
    private JsonParser jsonParser;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {

            seedDatabase();
        };
    }

    private void seedDatabase() {
        UserDto[] usersDtos = this.jsonParser.objectFromFile(UserDto[].class, SEED_USERS_JSON);
        this.userService.saveAll(usersDtos);

        CategoryDto[] categoriesDtos = this.jsonParser.objectFromFile(CategoryDto[].class, SEED_CATEGORIES_JSON);
        this.categoryService.saveAll(categoriesDtos);

        ProductDto[] productDtos = this.jsonParser.objectFromFile(ProductDto[].class, SEED_PRODUCTS_JSON);
        this.productService.saveAll(productDtos);
    }
}
