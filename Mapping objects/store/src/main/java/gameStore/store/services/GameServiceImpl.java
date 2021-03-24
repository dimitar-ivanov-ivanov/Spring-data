package gameStore.store.services;

import gameStore.store.models.dto.GameBindingModel;
import gameStore.store.models.entity.Game;
import gameStore.store.repository.GameRepository;
import gameStore.store.services.interfaces.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Pattern pattern = Pattern.compile("^https:\\/\\/www\\.youtube\\.com\\/watch\\?v=([a-zA-Z]{11})$");
        Matcher matcher = pattern.matcher(model.getTrailer());

        if (matcher.matches()) {
            //only save the last 11 characters, the link is always the same
            String videoUrl = matcher.group(1);
            game.setTrailer(videoUrl);
        } else {
            return false;
        }

        this.gameRepository.save(game);
        return true;
    }

    @Override
    public void editGame(long id, GameBindingModel model) {
        this.gameRepository.updateGame(
                id,
                model.getTitle(),
                model.getTrailer(),
                model.getThumbnail(),
                model.getPrice(),
                model.getSize(),
                model.getDescription()
        );
    }
}

