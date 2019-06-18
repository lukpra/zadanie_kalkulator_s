package pl.lukpra.kalkulators.messages.kalkulators.internal;

public class SalaryPayloadBuilder {

    public static SalaryPayload build(Double nettoSalary, Integer dailyWage, String currencyCode, String countryName) {
        SalaryPayload payload = new SalaryPayload();

        payload.setNetto(String.format("%s %s", nettoSalary, currencyCode));
        payload.setCountry(countryName);
        payload.setDailyWage(String.format("%d %s", dailyWage, currencyCode));

        return payload;
    }
}
