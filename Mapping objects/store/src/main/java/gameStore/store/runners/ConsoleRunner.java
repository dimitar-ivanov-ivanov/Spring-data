package gameStore.store.runners;

import gameStore.store.models.entity.Game;
import gameStore.store.models.entity.Order;
import gameStore.store.models.entity.Role;
import gameStore.store.models.entity.User;
import gameStore.store.models.dto.UserRegisterBindingModel;
import gameStore.store.repository.GameRepository;
import gameStore.store.repository.OrderRepository;
import gameStore.store.repository.RoleRepository;
import gameStore.store.repository.UserRepository;
import gameStore.store.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
public class ConsoleRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            User user = new User(
                    "pirate@gmail.com",
                    "12345",
                    "pirate",
                    "ivanov"
            );

            Game game = new Game(
                    "Pirates of the Caribbean",
                    "https://youtube.com/...",
                    "https://google.com/..",
                    BigDecimal.valueOf(3),
                    "ddd",
                    LocalDate.of(1990, 12, 12)
            );

            Order order = new Order(
                    user
            );

            Role userRole = new Role("user");
            Role adminRole = new Role("admin");

            roleRepository.save(userRole);
            roleRepository.save(adminRole);

            user.getRoles().add(adminRole);
            user.getGames().add(game);
            userRepository.save(user);
            order.getGames().add(game);

            UserRegisterBindingModel model = new UserRegisterBindingModel("pesho.pesh@gmail.com", "Abc123", "Abc123", "Pesho", "Stoqnov");
            userService.registerUser(model);
            orderRepository.save(order);
        };
    }
}
