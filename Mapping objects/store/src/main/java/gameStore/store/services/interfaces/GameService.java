package gameStore.store.services.interfaces;

import gameStore.store.models.dto.GameBindingModel;

public interface GameService {

    boolean addGame(GameBindingModel model);
}
