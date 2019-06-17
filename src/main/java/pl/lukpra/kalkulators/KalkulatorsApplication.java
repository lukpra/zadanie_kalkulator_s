package pl.lukpra.kalkulators;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class KalkulatorsApplication {

    public static void main(String[] args) {
        SpringApplication.run(KalkulatorsApplication.class, args);
    }


}
