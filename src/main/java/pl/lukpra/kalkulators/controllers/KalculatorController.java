package pl.lukpra.kalkulators.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lukpra.kalkulators.messages.kalkulators.internal.CountryPayload;
import pl.lukpra.kalkulators.messages.kalkulators.internal.CountryTaxPayload;
import pl.lukpra.kalkulators.services.KalkulatorService;

import java.util.List;

@RestController("/salary")
public class KalculatorController {

    @Autowired
    private KalkulatorService kalkulatorService;

    @RequestMapping(value = "/countryCode/{countryCode}/tax")
    public CountryTaxPayload getCountryTaxRate(
            @PathVariable(name = "countryCoude") String countryCode
    ) {
        return kalkulatorService.getTaxByCountryCode(countryCode);
    }

    @RequestMapping(value = "/countryCode/", method = RequestMethod.GET)
    public List<CountryPayload> getAllCountries() {
        return kalkulatorService.getAllCountries();
    }

    @RequestMapping(value = "/countryCode/{countryCode}", method = RequestMethod.GET)
    public CountryPayload getCountryByCountryCode(
            @PathVariable(name = "countryCode") String countryCode
    ) {
        return kalkulatorService.getCountryByCountryCode(countryCode);
    }

    @RequestMapping(value = "/countryCode/", method = RequestMethod.POST)
    public CountryPayload createCountryByCountryCode(
            @RequestBody CountryPayload countryPayload

    ) {
        return kalkulatorService.createCountry(countryPayload);
    }

    @RequestMapping(value = "/countryCode/", method = RequestMethod.PUT)
    public CountryPayload updateCountryByCountryCode(
            @RequestBody CountryPayload countryPayload

    ) {
        return kalkulatorService.updateCountry(countryPayload);
    }

    @RequestMapping(value = "/countryCode/", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCountryByCountryCode(
            @RequestBody CountryPayload countryPayload

    ) {
        kalkulatorService.deleteCountry(countryPayload);

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "/countryCode/{countryCode}/calculate/", method = RequestMethod.GET)
    public String calculateSalary(
            @PathVariable(name = "countryCode") String countryCode,
            @RequestParam(name = "salary") Integer salary
    ) {
        return "index - todo zwroc frontend";
    }

}
