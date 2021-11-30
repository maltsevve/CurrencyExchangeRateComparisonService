package com.maltsevve.currencyexchangeratecomparisonservice.rest;

import com.maltsevve.currencyexchangeratecomparisonservice.service.GiphyService;
import com.maltsevve.currencyexchangeratecomparisonservice.service.OpenExchangeRatesService;
import com.maltsevve.currencyexchangeratecomparisonservice.util.FormattedYesterdaysDate;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/exchange-rates")
public class RestControllerV1 {
    private OpenExchangeRatesService openExchangeRatesService;
    private FormattedYesterdaysDate formattedYesterdaysDate;
    private GiphyService giphyService;

    @GetMapping(value = "/{currencyCode}")
    public ResponseEntity getExchangeRates(@PathVariable("currencyCode") String currencyCode) {
        try {
            boolean richOrBroke = openExchangeRatesService
                    .exchangeRatesComparison(formattedYesterdaysDate.getYesterdayDate(), currencyCode);

            try {
                HttpHeaders headers = new HttpHeaders();
                headers.add("Location", giphyService.getRandomGif(richOrBroke));

                return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
            } catch (NullPointerException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Failed to generate a response.", e);
            }
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect currency code.", e);
        }
    }
}
