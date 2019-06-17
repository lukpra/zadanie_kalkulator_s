package pl.lukpra.kalkulators.messages.kalkulators.external.nbp;

import java.util.List;

public class TableResponsePayload {

    private String table;
    private String currency;
    private String code;
    private List<CurrencyRate> rates;

    public TableResponsePayload() {}

    public TableResponsePayload(String table, String currency, String code, List<CurrencyRate> rates) {
        this.table = table;
        this.currency = currency;
        this.code = code;
        this.rates = rates;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<CurrencyRate> getRates() {
        return rates;
    }

    public void setRates(List<CurrencyRate> rates) {
        this.rates = rates;
    }
}
