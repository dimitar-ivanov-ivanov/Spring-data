package gameStore.store.services.interfaces;

import gameStore.store.exceptions.ConfirmedPasswordIsWrongException;
import gameStore.store.models.dto.UserLoginBindingModel;
import gameStore.store.models.dto.UserRegisterBindingModel;
import gameStore.store.models.entity.Game;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserService {

    boolean registerUser(UserRegisterBindingModel model);

    boolean loginUser(UserLoginBindingModel model);

    boolean logout();

    List<Game> getGamesOfLoggedInUser();

}