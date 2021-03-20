package gameStore.store.runners;

import gameStore.store.models.Game;
import gameStore.store.models.Order;
import gameStore.store.models.User;
import gameStore.store.repository.GameRepository;
import gameStore.store.repository.OrderRepository;
import gameStore.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Configuration
public class ConsoleRunner {

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
                    "ivanov",
                    true
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

            user.getGames().add(game);
            order.getGames().add(game);

            userRepository.save(user);
            orderRepository.save(order);
        };
    }
}
