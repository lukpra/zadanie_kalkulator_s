package pl.lukpra.kalkulators.utils;

/*
    Translates ISO 3166 country code into currency ISO 4217
    examples: PL -> PLN
    US -> USD etc.
 */
public enum CountryCodeTranslator {
    PL(CurrencyCodeToCountryTranslator.PLN),
    UK(CurrencyCodeToCountryTranslator.GBP),
    DE(CurrencyCodeToCountryTranslator.EUR),
    ;

    CountryCodeTranslator(CurrencyCodeToCountryTranslator pln) {

    }
}
