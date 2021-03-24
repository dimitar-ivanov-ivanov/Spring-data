package gameStore.store.services.interfaces;

import gameStore.store.exceptions.ConfirmedPasswordIsWrongException;
import gameStore.store.models.dto.UserLoginBindingModel;
import gameStore.store.models.dto.UserRegisterBindingModel;

public interface UserService {

    boolean registerUser(UserRegisterBindingModel model);

    boolean loginUser(UserLoginBindingModel model);

    boolean logout();
}