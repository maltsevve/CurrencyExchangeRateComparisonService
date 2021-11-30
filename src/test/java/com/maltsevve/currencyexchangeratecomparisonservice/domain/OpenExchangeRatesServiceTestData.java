package com.maltsevve.currencyexchangeratecomparisonservice.domain;

import com.maltsevve.currencyexchangeratecomparisonservice.model.ExchangeRate;
import com.maltsevve.currencyexchangeratecomparisonservice.util.FormattedYesterdaysDate;

import java.util.HashMap;
import java.util.Map;

public class OpenExchangeRatesServiceTestData {
    public static ExchangeRate yesterdayExchangeRate() {
        ExchangeRate yesterdayExchangeRate = new ExchangeRate();
        yesterdayExchangeRate.setRates(OpenExchangeRatesServiceTestData.getYesterdayRates());

        return yesterdayExchangeRate;
    }

    public static ExchangeRate latestExchangeRate() {
        ExchangeRate latestExchangeRate = new ExchangeRate();
        latestExchangeRate.setRates(OpenExchangeRatesServiceTestData.getLatestRates());

        return latestExchangeRate;
    }

    public static Map<String, Double> getYesterdayRates() {
        Map<String, Double> yesterdayRates = new HashMap<>();
        yesterdayRates.put("RUB", 80.0);
        yesterdayRates.put("USD", 1.0);

        return yesterdayRates;
    }

    public static Map<String, Double> getLatestRates() {
        Map<String, Double> actualRates = new HashMap<>();
        actualRates.put("RUB", 79.0);
        actualRates.put("USD", 1.0);

        return actualRates;
    }

    public static String yesterdayDate() {
        FormattedYesterdaysDate formattedYesterdaysDate = new FormattedYesterdaysDate();
        return formattedYesterdaysDate.getYesterdayDate();
    }
}
