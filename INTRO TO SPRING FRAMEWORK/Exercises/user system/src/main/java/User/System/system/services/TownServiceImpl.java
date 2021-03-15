package User.System.system.services;

import User.System.system.models.Town;
import User.System.system.repositories.TownRepository;
import User.System.system.services.interfaces.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TownServiceImpl implements TownService {

    private TownRepository townRepository;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    @Override
    public void registerTown(Town town) {
        this.townRepository.save(town);
    }

    @Override
    public void registerTowns(Iterable<Town> towns) {
        this.townRepository.saveAll(towns);
    }
}
