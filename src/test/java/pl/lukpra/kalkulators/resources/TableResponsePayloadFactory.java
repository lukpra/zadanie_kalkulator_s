package pl.lukpra.kalkulators.resources;

import pl.lukpra.kalkulators.messages.kalkulators.external.nbp.TableResponsePayload;
import pl.lukpra.kalkulators.models.resources.CountryFactory;

import java.util.Arrays;
import java.util.Collections;

public class TableResponsePayloadFactory {

    public static final String DEFAULT_TABLE = "A";

    public static TableResponsePayload createDefaultEURResponse() {
        return new TableResponsePayload(
                DEFAULT_TABLE,
                CountryFactory.GERMANY_CURRENCY_CODE,
                CountryFactory.GERMANY_CURRENCY_CODE,
                CurrencyRateFactory.createDefaultCurrencyRateForSingleCurrency()
        );
    }

    public static TableResponsePayload createDefaultGBResponse() {
        return new TableResponsePayload(
                DEFAULT_TABLE,
                CountryFactory.GREAT_BRITAIN_CURRENCY_CODE,
                CountryFactory.GREAT_BRITAIN_CURRENCY_CODE,
                CurrencyRateFactory.createDefaultCurrencyRateForSingleCurrency()
        );
    }

    public static TableResponsePayload createMalformedGBResponseWithoutRates() {
        TableResponsePayload defaultGBResponse = createDefaultGBResponse();
        defaultGBResponse.setRates(Collections.emptyList());

        return defaultGBResponse;
    }

}
