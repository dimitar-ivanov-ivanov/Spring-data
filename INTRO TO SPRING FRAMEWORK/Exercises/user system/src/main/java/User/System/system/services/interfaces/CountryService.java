package User.System.system.services.interfaces;

import User.System.system.models.Country;
import User.System.system.models.Town;

public interface CountryService {

    void registerCountry(Country country);

    void registerCountries(Iterable<Country> countries);
}
