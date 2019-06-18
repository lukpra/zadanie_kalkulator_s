package pl.lukpra.kalkulators.messages.kalkulators.internal;

public class SalaryPayload {

    private String netto;
    private String country;
    private String dailyWage;

    public SalaryPayload() {}

    public SalaryPayload(String netto, String country, String dailyWage) {
        this.netto = netto;
        this.country = country;
        this.dailyWage = dailyWage;
    }

    public String getNetto() {
        return netto;
    }

    public void setNetto(String netto) {
        this.netto = netto;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDailyWage() {
        return dailyWage;
    }

    public void setDailyWage(String dailyWage) {
        this.dailyWage = dailyWage;
    }
}
