package User.System.system.services.interfaces;

import User.System.system.models.Town;

public interface TownService {

    void registerTown(Town town);

    void registerTowns(Iterable<Town> towns);
}
