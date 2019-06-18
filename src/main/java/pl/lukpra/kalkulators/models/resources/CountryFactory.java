package pl.lukpra.kalkulators.models.resources;

import pl.lukpra.kalkulators.models.entities.CountryEntity;

import java.util.Arrays;
import java.util.List;

/*
    Should be present in test however class is used for database population on startup.
 */
public class CountryFactory {

    public static final String POLAND_COUNTRY_CODE = "PL";
    public static final String POLAND_CURRENCY_CODE = "PLN";
    public static final Integer POLAND_TAX_RATE = 19;
    public static final Integer POLAND_FLAT_TAX_RATE = 1200;

    public static final String GERMANY_COUNTRY_CODE = "DE";
    public static final String GERMANY_CURRENCY_CODE = "EUR";
    public static final Integer GERMANY_TAX_RATE = 20;
    public static final Integer GERMANY_FLAT_TAX_RATE = 800;

    public static final String GREAT_BRITAIN_COUNTRY_CODE = "GB";
    public static final String GREAT_BRITAIN_CURRENCY_CODE = "GBP";
    public static final Integer GREAT_BRITAIN_TAX_RATE = 25;
    public static final Integer GREAT_BRITAIN_FLAT_TAX_RATE = 600;

    public static List<CountryEntity> createDefaultCountries() {
        return Arrays.asList(
                createPolandEntity(),
                createGermanyEntity(),
                createUnitedKingdomEntity()

        );
    }

    public static CountryEntity createPolandEntity() {
        CountryEntity poland = new CountryEntity();
        poland.setCountryCode(POLAND_COUNTRY_CODE);
        poland.setCurrencyCode(POLAND_CURRENCY_CODE);
        poland.setTaxRate(POLAND_TAX_RATE);
        poland.setTaxFlatRate(POLAND_FLAT_TAX_RATE);

        return poland;
    }

    public static CountryEntity createGermanyEntity() {
        CountryEntity germany = new CountryEntity();
        germany.setCountryCode(GERMANY_COUNTRY_CODE);
        germany.setCurrencyCode(GERMANY_CURRENCY_CODE);
        germany.setTaxRate(GERMANY_TAX_RATE);
        germany.setTaxFlatRate(GERMANY_FLAT_TAX_RATE);

        return germany;
    }

    public static CountryEntity createUnitedKingdomEntity() {
        CountryEntity unitedKingdom = new CountryEntity();
        unitedKingdom.setCountryCode(GREAT_BRITAIN_COUNTRY_CODE);
        unitedKingdom.setCurrencyCode(GREAT_BRITAIN_CURRENCY_CODE);
        unitedKingdom.setTaxRate(GREAT_BRITAIN_TAX_RATE);
        unitedKingdom.setTaxFlatRate(GREAT_BRITAIN_FLAT_TAX_RATE);

        return unitedKingdom;
    }

}
