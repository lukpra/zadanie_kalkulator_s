package pl.lukpra.kalkulators.models.entities;

import javax.persistence.*;

@Entity(name = "country")
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "country_code", unique = true)
    private String countryCode; // ISO 3166

    @Column(name = "currency_code")
    private String currencyCode; // ISO 4217

    @Column(name = "tax_rate")
    private Integer taxRate;

    @Column(name = "tax_flat_rate")
    private Integer taxFlatRate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
