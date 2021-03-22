package gameStore.store.services.interfaces;

import gameStore.store.exceptions.ConfirmedPasswordIsWrongException;
import gameStore.store.models.dto.UserRegisterBindingModel;

public interface UserService {

    boolean registerUser(UserRegisterBindingModel userRegisterBindingModel) throws ConfirmedPasswordIsWrongException;
}