package pl.lukpra.kalkulators.services;

import org.springframework.stereotype.Service;
import pl.lukpra.kalkulators.messages.kalkulators.internal.CountryPayload;
import pl.lukpra.kalkulators.messages.kalkulators.internal.CountryTaxPayload;

import java.util.List;

@Service
public interface KalkulatorService {

    CountryTaxPayload getTaxByCountryCode(String countryCode);

    CountryPayload getCountryByCountryCode(String countryCode);

    CountryPayload createCountry(CountryPayload countryPayload);

    CountryPayload updateCountry(CountryPayload countryPayload);

    void deleteCountry(CountryPayload countryPayload);

    List<CountryPayload> getAllCountries();
}
