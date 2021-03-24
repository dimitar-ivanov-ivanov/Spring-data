package gameStore.store.services;

import gameStore.store.models.dto.GameBindingModel;
import gameStore.store.models.entity.Game;
import gameStore.store.repository.GameRepository;
import gameStore.store.services.interfaces.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean addGame(GameBindingModel model) {
        Game game = this.modelMapper.map(model, Game.class);
        this.gameRepository.save(game);
        return true;
    }
}
