package pl.lukpra.kalkulators.resources;

import pl.lukpra.kalkulators.messages.kalkulators.external.nbp.CurrencyRate;

import java.util.Collections;
import java.util.List;

public class CurrencyRateFactory {

    public static final String DEFAULT_NUMBER = "117/A/NBP/2019";
    public static final String DEFAULT_EFFECTIVE_DATE = "2019-06-18";
    public static final Double DEFAULT_MID = 4.20;

    public static List<CurrencyRate> createDefaultCurrencyRateForSingleCurrency() {
        return Collections.singletonList(
                new CurrencyRate(
                        DEFAULT_NUMBER,
                        DEFAULT_EFFECTIVE_DATE,
                        DEFAULT_MID
                )
        );
    }
}
