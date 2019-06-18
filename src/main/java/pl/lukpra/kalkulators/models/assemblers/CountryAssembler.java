package pl.lukpra.kalkulators.models.assemblers;

import org.springframework.stereotype.Component;
import pl.lukpra.kalkulators.messages.kalkulators.internal.CountryPayload;
import pl.lukpra.kalkulators.models.entity.CountryEntity;

import java.util.Objects;

@Component
public class CountryAssembler {

    public CountryEntity assemblyFromPayload(CountryPayload countryPayload) {
        if (Objects.isNull(countryPayload))
            return null;

        CountryEntity newCountryEntity = new CountryEntity();

        newCountryEntity.setCountryCode(countryPayload.getCountryCode());
        newCountryEntity.setCurrencyCode(countryPayload.getCurrencyCode());
        newCountryEntity.setTaxRate(countryPayload.getTaxRate());
        newCountryEntity.setTaxFlatRate(countryPayload.getTaxFlatRate());

        return newCountryEntity;
    }

    public CountryPayload assemblyFromEntity(CountryEntity countryEntity) {
        if (Objects.isNull(countryEntity))
            return null;

        return new CountryPayload(
                countryEntity.getCountryCode(),
                countryEntity.getCurrencyCode(),
                countryEntity.getTaxRate(),
                countryEntity.getTaxFlatRate()
        );
    }
}
