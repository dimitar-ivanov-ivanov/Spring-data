package productShop.shop.runners;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import productShop.shop.models.dto.binding.CategoryDto;
import productShop.shop.models.dto.binding.ProductDto;
import productShop.shop.models.dto.view.ProductNamePriceSellerNameDto;
import productShop.shop.services.interfaces.CategoryService;
import productShop.shop.services.interfaces.ProductService;
import productShop.shop.utils.JsonParser;
import productShop.shop.models.dto.binding.UserDto;
import productShop.shop.services.interfaces.UserService;

import java.math.BigDecimal;

@Configuration
public class Runner {

    private static final String RESOURCES_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\";
    private static final String SEED_USERS_JSON = RESOURCES_PATH + "json\\users.json";
    private static final String SEED_CATEGORIES_JSON = RESOURCES_PATH + "json\\categories.json";
    private static final String SEED_PRODUCTS_JSON = RESOURCES_PATH + "json\\products.json";
    private static final String EXPORT_PRODUCTS_IN_RANGE_JSON = RESOURCES_PATH + "json\\products_in_range.json";


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

            //seedDatabase();

            exportProductsInRange();


        };
    }

    private void exportProductsInRange() {
        BigDecimal lower = new BigDecimal(500);
        BigDecimal upper = new BigDecimal(1000);

        List<ProductNamePriceSellerNameDto> dtos = productService.getProductByPriceBetween(lower, upper);
        this.jsonParser.objectToFile(dtos, EXPORT_PRODUCTS_IN_RANGE_JSON);
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
