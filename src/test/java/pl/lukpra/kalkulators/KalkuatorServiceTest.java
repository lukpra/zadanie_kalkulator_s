package pl.lukpra.kalkulators;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.lukpra.kalkulators.messages.kalkulators.internal.CountryPayload;
import pl.lukpra.kalkulators.messages.kalkulators.internal.CountryTaxPayload;
import pl.lukpra.kalkulators.messages.kalkulators.internal.SalaryPayload;
import pl.lukpra.kalkulators.models.entity.CountryEntity;
import pl.lukpra.kalkulators.models.repository.CountryRepository;
import pl.lukpra.kalkulators.models.resources.CountryFactory;
import pl.lukpra.kalkulators.resources.TableResponsePayloadFactory;
import pl.lukpra.kalkulators.services.NBPService;
import pl.lukpra.kalkulators.services.impl.KalkulatorServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KalkuatorServiceTest {

    @Mock
    private NBPService nbpService;

    @Autowired
    @InjectMocks
    private KalkulatorServiceImpl kalkulatorService;

    @Autowired
    private CountryRepository countryRepository;

    @Before()
    public void setUp() {
        countryRepository.saveAll(CountryFactory.createDefaultCountries());
        MockitoAnnotations.initMocks(this);
    }

    @After()
    public void cleanUp() {
        countryRepository.deleteAll();
    }

    @Test
    public void shouldCalculateNettoSalaryForGermany() {
        // Given
        Mockito.when(nbpService.getCurrencyRatesForCurrencyCode(CountryFactory.GERMANY_CURRENCY_CODE))
                .thenReturn(TableResponsePayloadFactory.createDefaultEURResponse());
        String countryCode = CountryFactory.GERMANY_COUNTRY_CODE;
        Integer dailyWage = 200;

        // When
        SalaryPayload salary = kalkulatorService.calculateSalary(countryCode, dailyWage);

        // Then
        Assert.assertEquals(dailyWage + " EUR", salary.getDailyWage());
        Assert.assertEquals("198.1 EUR", salary.getNetto());
        Assert.assertEquals(countryCode, salary.getCountry());
    }

    @Test
    public void shouldCalculateNettoSalaryForGreatBritain() {
        // Given
        Mockito.when(nbpService.getCurrencyRatesForCurrencyCode(CountryFactory.GREAT_BRITAIN_CURRENCY_CODE))
                .thenReturn(TableResponsePayloadFactory.createDefaultGBResponse());
        String countryCode = CountryFactory.GREAT_BRITAIN_COUNTRY_CODE;
        Integer dailyWage = 332;

        // When
        SalaryPayload salary = kalkulatorService.calculateSalary(countryCode, dailyWage);

        // Then
        Assert.assertEquals(dailyWage + " GBP", salary.getDailyWage());
        Assert.assertEquals("854.29 GBP", salary.getNetto());
        Assert.assertEquals(countryCode, salary.getCountry());
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowWhenApiReturnedNoRates() {
        // Given
        Mockito.when(nbpService.getCurrencyRatesForCurrencyCode(CountryFactory.GREAT_BRITAIN_CURRENCY_CODE))
                .thenReturn(TableResponsePayloadFactory.createMalformedGBResponseWithoutRates());

        String countryCode = CountryFactory.GREAT_BRITAIN_COUNTRY_CODE;
        Integer dailyWage = 0;

        // When
        kalkulatorService.calculateSalary(countryCode, dailyWage);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenDailyWageEqualsZero() {
        // Given
        Integer dailyWage = 0;
        String countryCode = CountryFactory.POLAND_COUNTRY_CODE;

        // When
        kalkulatorService.calculateSalary(countryCode, dailyWage);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenDailyWageIsBelowZero() {
        // Given
        Integer dailyWage = -15;
        String countryCode = CountryFactory.POLAND_COUNTRY_CODE;

        // When
        kalkulatorService.calculateSalary(countryCode, dailyWage);
    }

    @Test
    public void shouldGetCountryTaxByCountryCode() {
        // Given
        CountryEntity expectedCountry = CountryFactory.createPolandEntity();

        // When
        CountryTaxPayload actualCountry = kalkulatorService.getTaxByCountryCode(expectedCountry.getCountryCode());

        // Then
        Assert.assertEquals(expectedCountry.getTaxFlatRate(), actualCountry.getTaxFlatRate());
        Assert.assertEquals(expectedCountry.getTaxRate(), actualCountry.getTaxRate());
    }

    @Test
    public void shouldGetCountryTaxByCountryCodeInLowerCase() {
        // Given
        CountryEntity expectedCountry = CountryFactory.createPolandEntity();

        // When
        CountryTaxPayload actualCountry = kalkulatorService.getTaxByCountryCode(expectedCountry.getCountryCode().toLowerCase());

        // Then
        Assert.assertEquals(expectedCountry.getTaxFlatRate(), actualCountry.getTaxFlatRate());
        Assert.assertEquals(expectedCountry.getTaxRate(), actualCountry.getTaxRate());
    }

    @Test
    public void shouldGetCountryByCountryCode() {
        // Given
        CountryEntity expectedCountry = CountryFactory.createPolandEntity();

        // When
        CountryPayload actualCountry = kalkulatorService.getCountryByCountryCode(expectedCountry.getCountryCode());

        // Then
        Assert.assertEquals(expectedCountry.getCountryCode(), actualCountry.getCountryCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenCountryDoesNotExist() {
        // Given
        String malformedPolandCountryCode = String.format("%s bazinga!", CountryFactory.POLAND_COUNTRY_CODE);

        // When
        kalkulatorService.getCountryByCountryCode(malformedPolandCountryCode);
    }

}
