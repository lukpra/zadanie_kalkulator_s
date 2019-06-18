package pl.lukpra.kalkulators.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.lukpra.kalkulators.messages.kalkulators.external.nbp.TableResponsePayload;

import java.util.List;

@FeignClient(value = "nbp",
        url = "http://api.nbp.pl/api/")
public interface NBPService {

    String RESPONSE_IN_JSON = "?format=JSON";
    String TABLE_WITH_MOST_POPULAR_CURRENCIES = "A";

    @RequestMapping(method = RequestMethod.GET,
            value = "exchangerates/rates/" + TABLE_WITH_MOST_POPULAR_CURRENCIES + "/{currency_code}/",
            params = RESPONSE_IN_JSON
    )
    TableResponsePayload getCurrencyRatesForCurrencyCode(@PathVariable("currency_code") String currencyCode);

}
