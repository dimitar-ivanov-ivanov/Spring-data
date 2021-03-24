package gameStore.store.services.interfaces;

import gameStore.store.exceptions.ConfirmedPasswordIsWrongException;
import gameStore.store.models.dto.UserLoginBindingModel;
import gameStore.store.models.dto.UserRegisterBindingModel;
import gameStore.store.models.entity.Game;
import gameStore.store.models.entity.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserService {

    boolean registerUser(UserRegisterBindingModel model);

    boolean loginUser(UserLoginBindingModel model);

    boolean logout();

    List<Game> getGamesOfLoggedInUser();

    public User getLoggedInUser();

    void addItemToCart(String title);

    void removeItemFromCart(String title);

    void buyItemsFromCart();

}