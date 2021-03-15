package User.System.system.services;

import User.System.system.models.Country;
import User.System.system.repositories.CountryRepository;
import User.System.system.services.interfaces.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {

    private CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public void registerCountry(Country country) {
        this.countryRepository.save(country);
    }

    @Override
    public void registerCountries(Iterable<Country> countries) {
        this.countryRepository.saveAll(countries);

    }
}
