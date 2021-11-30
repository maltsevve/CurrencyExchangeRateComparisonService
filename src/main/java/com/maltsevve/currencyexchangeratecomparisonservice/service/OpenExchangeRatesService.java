package com.maltsevve.currencyexchangeratecomparisonservice.service;

import com.maltsevve.currencyexchangeratecomparisonservice.model.ExchangeRate;
import com.maltsevve.currencyexchangeratecomparisonservice.client.OpenExchangeRatesClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OpenExchangeRatesService {
    @Value("${openExchangeRatesAppId}")
    private String appId;
    @Value("${baseCurrency}")
    private String baseCurrency;

    private final OpenExchangeRatesClient openExchangeRatesClient;

    public OpenExchangeRatesService(OpenExchangeRatesClient openExchangeRatesClient) {
        this.openExchangeRatesClient = openExchangeRatesClient;
    }

    public boolean exchangeRatesComparison(String date, String requiredCurrency) {
        Map<String, Double> yesterdayRates = getExchangeRateOnDate(date).getRates();
        Map<String, Double> latestRates = getLatestExchangeRate().getRates();

//        if (!yesterdayRates.containsKey(requiredCurrency) && !latestRates.containsKey(requiredCurrency)) {
//            return null;
//        }

        double yesterdayRate = comparisonOfRatesAgainstUsd(yesterdayRates, requiredCurrency);
        double latestRate = comparisonOfRatesAgainstUsd(latestRates, requiredCurrency);

        return yesterdayRate > latestRate;
    }

    private double comparisonOfRatesAgainstUsd(Map<String, Double> currentRates, String requiredCurrency) {
        double baseCurrencyRate = currentRates.get(baseCurrency);
        double latestCurrencyRate = currentRates.get(requiredCurrency);

        return latestCurrencyRate / baseCurrencyRate;
    }

    private ExchangeRate getExchangeRateOnDate(String date) {
        return openExchangeRatesClient.getExchangeRateOnDate(date, appId);
    }

    private ExchangeRate getLatestExchangeRate() {
        return openExchangeRatesClient.getLatestExchangeRate(appId);
    }
}
