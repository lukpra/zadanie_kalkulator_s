package pl.lukpra.kalkulators.messages.kalkulators.internal;

public class CountryTaxPayload {

    private Integer taxRate;

    private Integer taxFlatRate;

    public CountryTaxPayload() {
    }

    public CountryTaxPayload(Integer taxRate, Integer taxFlatRate) {
        this.taxRate = taxRate;
        this.taxFlatRate = taxFlatRate;
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
