package pl.lukpra.kalkulators.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lukpra.kalkulators.messages.kalkulators.external.nbp.CurrencyRate;
import pl.lukpra.kalkulators.messages.kalkulators.external.nbp.TableResponsePayload;
import pl.lukpra.kalkulators.messages.kalkulators.internal.CountryPayload;
import pl.lukpra.kalkulators.messages.kalkulators.internal.CountryTaxPayload;
import pl.lukpra.kalkulators.messages.kalkulators.internal.SalaryPayload;
import pl.lukpra.kalkulators.messages.kalkulators.internal.builders.SalaryPayloadBuilder;
import pl.lukpra.kalkulators.models.assemblers.CountryAssembler;
import pl.lukpra.kalkulators.models.assemblers.CountryTaxAssembler;
import pl.lukpra.kalkulators.models.entities.CountryEntity;
import pl.lukpra.kalkulators.models.repository.CountryRepository;
import pl.lukpra.kalkulators.services.KalkulatorService;
import pl.lukpra.kalkulators.services.NBPService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class KalkulatorServiceImpl implements KalkulatorService {

    private final static Integer AVERAGE_AMOUNT_OF_WORKING_DAYS_IN_MONTH = 22;
    private final static String SERVICE_LOCATION_CURRENCY = "PLN";
    private final static int SALARY_CALCULATION_PRECISION = 2;

    @Autowired
    private NBPService nbpService;

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

        if (doesGivenCountryExistsAlready)
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
    public SalaryPayload calculateSalary(String countryCode, Integer dailyWage) {
        CountryEntity matchingCountry = findCountryByCountryCode(countryCode);

        if (dailyWage <= 0)
            throw new IllegalArgumentException("Salary cannot be lower or equal zero!");

        double salaryWithoutTaxes = dailyWage * AVERAGE_AMOUNT_OF_WORKING_DAYS_IN_MONTH;

        if (!matchingCountry.getCurrencyCode().equals(SERVICE_LOCATION_CURRENCY)) { // Currency conversion is required
            TableResponsePayload currencyRates = nbpService.getCurrencyRatesForCurrencyCode(matchingCountry.getCurrencyCode());
            Double currencyConversionRate = currencyRates.getRates().stream()
                    .findFirst().map(CurrencyRate::getMid)
                    .orElseThrow(() -> new IllegalStateException("No conversion rates were returned by currency service!"));

            salaryWithoutTaxes = salaryWithoutTaxes / currencyConversionRate;
        }

        double taxesToSubtract = calculateTaxesToPay(salaryWithoutTaxes, matchingCountry.getTaxFlatRate(), matchingCountry.getTaxRate());
        double nettoSalary = salaryWithoutTaxes - taxesToSubtract;

        return SalaryPayloadBuilder.build(
                BigDecimal.valueOf(nettoSalary)
                        .setScale(SALARY_CALCULATION_PRECISION, RoundingMode.HALF_UP)
                        .doubleValue(),
                dailyWage,
                matchingCountry.getCurrencyCode(),
                matchingCountry.getCountryCode()
        );
    }

    private CountryEntity findCountryByCountryCode(String countryCode) {
        return countryRepository.findByCountryCode(countryCode.toUpperCase())
                .orElseThrow(() -> new IllegalArgumentException(String.format("Country with code: %s was not found!", countryCode)));
    }

    private Double calculateTaxesToPay(double salaryWithoutTaxes, Integer countryTaxFlatRate, Integer countryTaxPercentage) {
        double salaryAfterFlatRate = salaryWithoutTaxes - countryTaxFlatRate;

        double taxPercentage = calculateTaxPercentage(salaryAfterFlatRate, countryTaxPercentage);

        return taxPercentage + countryTaxFlatRate;
    }

    private Double calculateTaxPercentage(double salary, Integer taxPercentage) {
        return (salary * taxPercentage) / 100;
    }
}
