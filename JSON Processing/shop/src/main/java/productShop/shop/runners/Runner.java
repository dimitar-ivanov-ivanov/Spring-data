package productShop.shop.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import productShop.shop.utils.JsonParser;
import productShop.shop.models.dto.UserDto;
import productShop.shop.services.interfaces.UserService;

@Configuration
public class Runner {

    private static final String RESOURCES_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\";
    private static final String SEED_USERS_JSON = RESOURCES_PATH + "json\\users.json";


    @Autowired
    private JsonParser jsonParser;

    @Autowired
    private UserService userService;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {

            seedDatabase();
        };
    }

    private void seedDatabase() {
        UserDto[] usersDto = this.jsonParser.objectFromFile(UserDto[].class, SEED_USERS_JSON);
        this.userService.saveAll(usersDto);
    }
}
