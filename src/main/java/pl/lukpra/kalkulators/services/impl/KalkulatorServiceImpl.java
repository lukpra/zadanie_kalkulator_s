package pl.lukpra.kalkulators.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lukpra.kalkulators.messages.kalkulators.internal.CountryPayload;
import pl.lukpra.kalkulators.messages.kalkulators.internal.CountryTaxPayload;
import pl.lukpra.kalkulators.models.assemblers.CountryAssembler;
import pl.lukpra.kalkulators.models.assemblers.CountryTaxAssembler;
import pl.lukpra.kalkulators.models.entity.CountryEntity;
import pl.lukpra.kalkulators.models.repository.CountryRepository;
import pl.lukpra.kalkulators.services.KalkulatorService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class KalkulatorServiceImpl implements KalkulatorService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountryAssembler countryAssembler;

    @Autowired
    private CountryTaxAssembler countryTaxAssembler;

    @Override
    public CountryTaxPayload getTaxByCountryCode(String countryCode) {
        CountryEntity countryEntity = findCountryByCountryCode(countryCode);

        return countryTaxAssembler.assemblyFromEntity(countryEntity);
    }

    @Override
    public CountryPayload getCountryByCountryCode(String countryCode) {
        CountryEntity countryEntity = findCountryByCountryCode(countryCode);

        return countryAssembler.assemblyFromEntity(countryEntity);
    }

    @Override
    public CountryPayload createCountry(CountryPayload countryPayload) {
        Boolean doesGivenCountryExistsAlready = countryRepository.existsByCountryCode(countryPayload.getCountryCode());

        if(doesGivenCountryExistsAlready)
            throw new IllegalStateException(String.format("Country with given code: %s already exists!", countryPayload.getCountryCode()));

        CountryEntity countryEntity = countryAssembler.assemblyFromPayload(countryPayload);
        CountryEntity savedEntity = countryRepository.save(countryEntity);

        return countryAssembler.assemblyFromEntity(savedEntity);
    }

    @Override
    public CountryPayload updateCountry(CountryPayload countryPayload) {
        CountryEntity currentCountryState = findCountryByCountryCode(countryPayload.getCountryCode());

        CountryEntity newCountryState = countryAssembler.assemblyFromPayload(countryPayload);
        newCountryState.setId(currentCountryState.getId());

        newCountryState = countryRepository.save(newCountryState);

        return countryAssembler.assemblyFromEntity(newCountryState);
    }

    @Override
    public void deleteCountry(CountryPayload countryPayload) {
        countryRepository.deleteByCountryCode(countryPayload.getCountryCode());
    }

    @Override
    public List<CountryPayload> getAllCountries() {
        return countryRepository.findAll().stream()
                .map(countryAssembler::assemblyFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Integer calculateSalary() {

       return 0;
    }

    public CountryEntity findCountryByCountryCode(String countryCode) {
        return countryRepository.findByCountryCode(countryCode)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Country with code: %s was not found!", countryCode)));
    }
}
