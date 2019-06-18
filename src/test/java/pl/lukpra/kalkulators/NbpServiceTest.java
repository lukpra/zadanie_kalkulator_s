package pl.lukpra.kalkulators;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.lukpra.kalkulators.messages.kalkulators.external.nbp.TableResponsePayload;
import pl.lukpra.kalkulators.models.resources.CountryFactory;
import pl.lukpra.kalkulators.services.NBPService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NbpServiceTest {

    @Autowired
    private NBPService nbpService;

    @Test
    public void shouldGetCurrentCoversionRate() {
        // Given
        String germanyCountryCode = CountryFactory.GERMANY_CURRENCY_CODE;

        // When
        TableResponsePayload currencyRatesForCountryCode = nbpService.getCurrencyRatesForCurrencyCode(germanyCountryCode);

        // Then
        Assert.assertNotNull(currencyRatesForCountryCode);
        Assert.assertEquals(CountryFactory.GERMANY_CURRENCY_CODE, currencyRatesForCountryCode.getCode());
    }

}
