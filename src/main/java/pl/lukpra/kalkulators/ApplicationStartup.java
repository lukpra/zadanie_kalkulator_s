package pl.lukpra.kalkulators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.lukpra.kalkulators.models.repository.CountryRepository;
import pl.lukpra.kalkulators.models.resources.CountryFactory;

@Component
@Profile("demo")
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        countryRepository.saveAll(CountryFactory.createDefaultCountries());
    }
}
