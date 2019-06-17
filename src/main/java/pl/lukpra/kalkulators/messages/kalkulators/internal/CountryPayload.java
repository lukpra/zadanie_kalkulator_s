package pl.lukpra.kalkulators.messages.kalkulators.internal;

public class CountryPayload {

    private String countryCode; // ISO 3166

    private String currencyCode; // ISO 4217

    private Integer taxRate;

    private Integer taxFlatRate;

    public CountryPayload() {
    }

    public CountryPayload(String countryCode, String currencyCode, Integer taxRate, Integer taxFlatRate) {
        this.countryCode = countryCode;
        this.currencyCode = currencyCode;
        this.taxRate = taxRate;
        this.taxFlatRate = taxFlatRate;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Integer getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Integer taxRate) {
        this.taxRate = taxRate;
    }

    public Integer getTaxFlatRate() {
        return taxFlatRate;
    }

    public void setTaxFlatRate(Integer taxFlatRate) {
        this.taxFlatRate = taxFlatRate;
    }
}
