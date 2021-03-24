package gameStore.store.services.interfaces;

import gameStore.store.models.dto.GameBindingModel;
import gameStore.store.models.entity.Game;

public interface GameService {

    boolean addGame(GameBindingModel model);

    void editGame(long id, GameBindingModel model);

    void deleteGame(long id);
}
