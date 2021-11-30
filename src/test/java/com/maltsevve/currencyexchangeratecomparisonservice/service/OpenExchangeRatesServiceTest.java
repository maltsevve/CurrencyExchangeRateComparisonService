package com.maltsevve.currencyexchangeratecomparisonservice.service;

import com.maltsevve.currencyexchangeratecomparisonservice.client.OpenExchangeRatesClient;
import com.maltsevve.currencyexchangeratecomparisonservice.domain.OpenExchangeRatesServiceTestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
class OpenExchangeRatesServiceTest {
    @Autowired
    OpenExchangeRatesService openExchangeRatesService;

    @MockBean
    OpenExchangeRatesClient openExchangeRatesClient;

    @Value("${openExchangeRatesAppId}")
    private String appId;

    @Test
    void shouldReturnTrueOrFalseStringIfCoursesAreDifferent() {
        String date = OpenExchangeRatesServiceTestData.yesterdayDate();

        given(openExchangeRatesClient.getExchangeRateOnDate(date, appId))
                .willReturn(OpenExchangeRatesServiceTestData.yesterdayExchangeRate());
        given(openExchangeRatesClient.getLatestExchangeRate(appId))
                .willReturn(OpenExchangeRatesServiceTestData.latestExchangeRate());

        boolean isYesterdayRateBigger = openExchangeRatesService.exchangeRatesComparison(date, "RUB");

        assertFalse(isYesterdayRateBigger);
    }
}