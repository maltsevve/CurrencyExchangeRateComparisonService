package com.maltsevve.currencyexchangeratecomparisonservice.client;

import com.maltsevve.currencyexchangeratecomparisonservice.model.ExchangeRate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "openexchangerate", url = "${openExchangeRatesUrl}")
public interface OpenExchangeRatesClient {
    @RequestMapping(value = "/historical/{date}.json", params = {"app_id"}, method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ExchangeRate getExchangeRateOnDate(@PathVariable String date, @RequestParam(value = "app_id") String appId);

    @RequestMapping(value = "/latest.json", params = {"app_id"}, method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ExchangeRate getLatestExchangeRate(@RequestParam(value = "app_id") String appId);
}
