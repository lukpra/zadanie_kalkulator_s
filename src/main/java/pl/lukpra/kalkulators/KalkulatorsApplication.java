package pl.lukpra.kalkulators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import pl.lukpra.kalkulators.models.repository.CountryRepository;

@SpringBootApplication(scanBasePackages = {"pl.lukpra.kalkulators"})
@EnableFeignClients
public class KalkulatorsApplication {

    @Autowired
    private CountryRepository countryRepository;

    public static void main(String[] args) {
        SpringApplication.run(KalkulatorsApplication.class, args);
    }

}
