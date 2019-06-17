package pl.lukpra.kalkulators.models.assemblers;

import org.springframework.stereotype.Component;
import pl.lukpra.kalkulators.messages.kalkulators.internal.CountryTaxPayload;
import pl.lukpra.kalkulators.models.entity.CountryEntity;

import java.util.Objects;

@Component
public class CountryTaxAssembler {

    public CountryTaxPayload assemblyFromEntity(CountryEntity countryEntity) {
        if(Objects.isNull(countryEntity))
            return null;

        return new CountryTaxPayload(
                countryEntity.getTaxRate(),
                countryEntity.getTaxFlatRate()
        );
    }
}
