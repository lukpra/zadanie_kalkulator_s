package pl.lukpra.kalkulators;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import pl.lukpra.kalkulators.models.repository.CountryRepository;
import pl.lukpra.kalkulators.models.resources.CountryFactory;

@SpringBootApplication(scanBasePackages = {"pl.lukpra.kalkulators"})
@EnableFeignClients
public class KalkulatorsApplication {

    @Autowired
    private CountryRepository countryRepository;

    public static void main(String[] args) {
        SpringApplication.run(KalkulatorsApplication.class, args);
    }

    @Bean
    InitializingBean setupDatabase() {
        return () -> {
//         countryRepository.saveAll(CountryFactory.createDefaultCountries());
        };
    }

}
