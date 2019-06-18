package pl.lukpra.kalkulators;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.lukpra.kalkulators.messages.kalkulators.internal.CountryPayload;
import pl.lukpra.kalkulators.messages.kalkulators.internal.CountryTaxPayload;
import pl.lukpra.kalkulators.models.assemblers.CountryTaxAssembler;
import pl.lukpra.kalkulators.models.entity.CountryEntity;
import pl.lukpra.kalkulators.models.repository.CountryRepository;
import pl.lukpra.kalkulators.models.resources.CountryFactory;
import pl.lukpra.kalkulators.services.KalkulatorService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KalkuatorServiceTest {

    // TODO: Mock NBPService and test calculation function

    @Autowired
    private KalkulatorService kalkulatorService;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountryTaxAssembler countryTaxAssembler;

//    @Before()
//    public void setUp() {
//        countryRepository.saveAll(CountryFactory.createDefaultCountries());
//    }
//
//    @After()
//    public void cleanUp() {
//        countryRepository.deleteAll();
//    }

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


}
