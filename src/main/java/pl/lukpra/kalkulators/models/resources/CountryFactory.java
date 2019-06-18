package pl.lukpra.kalkulators.models.resources;

import pl.lukpra.kalkulators.models.entity.CountryEntity;

import java.util.Arrays;
import java.util.List;

public class CountryFactory {

    public static final String  COUNTRY_1_CODE = "PL";
    public static final String  COUNTRY_1_CURRENCY_CODE = "PLN";
    public static final Integer COUNTRY_1_TAX_RATE = 19;
    public static final Integer COUNTRY_1_FLAT_TAX_RATE = 1200;

    public static final String  COUNTRY_2_CODE = "DE";
    public static final String  COUNTRY_2_CURRENCY_CODE = "EUR";
    public static final Integer COUNTRY_2_TAX_RATE = 20;
    public static final Integer COUNTRY_2_FLAT_TAX_RATE = 800;

    public static final String  COUNTRY_3_CODE = "GB";
    public static final String  COUNTRY_3_CURRENCY_CODE = "GBP";
    public static final Integer COUNTRY_3_TAX_RATE = 25;
    public static final Integer COUNTRY_3_FLAT_TAX_RATE = 600;

    public static List<CountryEntity> createDefaultCountries() {
        return Arrays.asList(
                createPolandEntity(),
                createGermanyEntity(),
                createUnitedKingdomEntity()

        );
    }

    public static CountryEntity createPolandEntity() {
        CountryEntity poland = new CountryEntity();
        poland.setCountryCode(COUNTRY_1_CODE);
        poland.setCurrencyCode(COUNTRY_1_CURRENCY_CODE);
        poland.setTaxRate(COUNTRY_1_TAX_RATE);
        poland.setTaxFlatRate(COUNTRY_1_FLAT_TAX_RATE);

        return poland;
    }

    public static CountryEntity createGermanyEntity() {
        CountryEntity germany = new CountryEntity();
        germany.setCountryCode(COUNTRY_2_CODE);
        germany.setCurrencyCode(COUNTRY_2_CURRENCY_CODE);
        germany.setTaxRate(COUNTRY_2_TAX_RATE);
        germany.setTaxFlatRate(COUNTRY_2_FLAT_TAX_RATE);

        return germany;
    }

    public static CountryEntity createUnitedKingdomEntity() {
        CountryEntity unitedKingdom = new CountryEntity();
        unitedKingdom.setCountryCode(COUNTRY_3_CODE);
        unitedKingdom.setCurrencyCode(COUNTRY_3_CURRENCY_CODE);
        unitedKingdom.setTaxRate(COUNTRY_3_TAX_RATE);
        unitedKingdom.setTaxFlatRate(COUNTRY_3_FLAT_TAX_RATE);

        return unitedKingdom;
    }

}
