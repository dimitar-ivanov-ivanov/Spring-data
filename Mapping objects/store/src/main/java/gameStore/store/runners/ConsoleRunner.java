package gameStore.store.runners;

import gameStore.store.models.dto.GameBindingModel;
import gameStore.store.models.dto.UserLoginBindingModel;
import gameStore.store.models.entity.Game;
import gameStore.store.models.entity.Order;
import gameStore.store.models.entity.Role;
import gameStore.store.models.entity.User;
import gameStore.store.models.dto.UserRegisterBindingModel;
import gameStore.store.repository.GameRepository;
import gameStore.store.repository.OrderRepository;
import gameStore.store.repository.RoleRepository;
import gameStore.store.repository.UserRepository;
import gameStore.store.services.interfaces.GameService;
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
    private GameService gameService;

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
                    "https://www.youtube.com/watch?v=edYCtaNueQY",
                    "https://google.com/..",
                    BigDecimal.valueOf(3.5),
                    100.5,
                    "This is a game about pirates being on ships etc etc.",
                    LocalDate.of(1990, 12, 12)
            );

            Order order = new Order(
                    user
            );

            Role userRole = new Role("user");
            Role adminRole = new Role("admin");

            user.getRoles().add(adminRole);
            user.getRoles().add(userRole);
            roleRepository.save(userRole);
            roleRepository.save(adminRole);

            user.getGames().add(game);
            gameRepository.save(game);
            userRepository.save(user);
            order.getGames().add(game);
            orderRepository.save(order);

            UserRegisterBindingModel model = new UserRegisterBindingModel("pesho.pesh@gmail.com", "Abc123", "Abc123", "Pesho", "Stoqnov");
            boolean isRegistered = userService.registerUser(model);

            System.out.println("Is registered: " + isRegistered);

            UserLoginBindingModel loginModel = new UserLoginBindingModel("pesho.pesh@gmail.com", "Abc123");
            boolean isLoggedIn = userService.loginUser(loginModel);

            System.out.println("Is logged in: " + isLoggedIn);

            boolean isLoggedOut = userService.logout();
            System.out.println("Is logged out: " + isLoggedOut);

            GameBindingModel game2 = new GameBindingModel(
                    "Pirates of the Caribbean 2",
                    "https://www.youtube.com/watch?v=edYCtaNueQY",
                    "https://google.com/..",
                    BigDecimal.valueOf(3.5),
                    100.5,
                    "This is a game about pirates being on ships etc etc."
            );

            System.out.println("Aded game: " + gameService.addGame(game2));
        };
    }
}
