package gameStore.store.services;

import gameStore.store.exceptions.ConfirmedPasswordIsWrongException;
import gameStore.store.models.dto.UserLoginBindingModel;
import gameStore.store.models.entity.Game;
import gameStore.store.models.entity.Role;
import gameStore.store.models.entity.User;
import gameStore.store.models.dto.UserRegisterBindingModel;
import gameStore.store.repository.UserRepository;
import gameStore.store.services.interfaces.GameService;
import gameStore.store.services.interfaces.RoleService;
import gameStore.store.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final GameService gameService;
    private User loggedInUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleService roleService, GameService gameService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.gameService = gameService;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    @Override
    public void addItemToCart(String title) {
        if (loggedInUser == null) {
            return;
        }

        Game game = gameService.getGameByTitle(title);
        boolean ownsGame = false;

        Set<Game> ownedGames = loggedInUser.getGames();

        for (Game ownedGame : ownedGames) {
            if (ownedGame.getId() == game.getId()) {
                return;
            }
        }

        loggedInUser.getCart().add(game);
        userRepository.save(loggedInUser);
    }

    @Override
    public void removeItemFromCart(String title) {
        if (loggedInUser == null) {
            return;
        }

        Game game = gameService.getGameByTitle(title);

        boolean gameIsInCart = false;
        Set<Game> cartGames = loggedInUser.getCart();
        for (Game cartGame : cartGames) {
            if (cartGame.getId() == game.getId()) {
                gameIsInCart = true;
            }
        }

        if (gameIsInCart) {
            loggedInUser.getCart().remove(game);
            userRepository.save(loggedInUser);
        }
    }

    @Override
    public void buyItemsFromCart() {
        if (loggedInUser == null) {
            return;
        }

        Set<Game> cartGames = loggedInUser.getCart();
        for (Game cartGame : cartGames) {
            loggedInUser.getGames().add(cartGame);
        }

        loggedInUser.setCart(new HashSet<>());
        userRepository.save(loggedInUser);
    }

    @Override
    public boolean registerUser(UserRegisterBindingModel model) {
        User user = this.modelMapper.map(model, User.class);
        Role role = this.setUserRole(user);
        if (model.doPasswordsMatch()) {
            user = this.userRepository.saveAndFlush(user);
            this.roleService.updateRole(role);
        } else {
            throw new ConfirmedPasswordIsWrongException("Passwords do not match.");
        }

        return true;
    }

    @Override
    public boolean loginUser(UserLoginBindingModel model) {
        User user = this.userRepository.getUserByEmailAndPassword(model.getEmail(), model.getPassword());

        if (user != null) {
            loggedInUser = user;
            return true;
        }

        return false;
    }

    @Override
    public boolean logout() {
        if (loggedInUser != null) {
            loggedInUser = null;
            return true;
        }
        return false;
    }

    @Override
    public List<Game> getGamesOfLoggedInUser() {
        List<Game> games = this.userRepository.getGamesOfUser(loggedInUser.getId());
        return games;
    }

    private Role setUserRole(User user) {
        Role role = null;
        if (this.userRepository.count() > 0) {
            role = this.roleService.getRoleByName(RoleService.Roles.USER);
            user.getRoles().add(role);
        } else {
            role = this.roleService.getRoleByName(RoleService.Roles.ADMIN);
            user.getRoles().add(role);
        }
        return role;
    }
}
