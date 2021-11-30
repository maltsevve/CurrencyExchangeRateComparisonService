package com.maltsevve.currencyexchangeratecomparisonservice.client;

import com.maltsevve.currencyexchangeratecomparisonservice.model.ExchangeRate;
import com.maltsevve.currencyexchangeratecomparisonservice.util.FormattedYesterdaysDate;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 9091)
class OpenExchangeRatesClientTest {
    @Autowired
    OpenExchangeRatesClient openExchangeRatesClient;

    @Autowired
    FormattedYesterdaysDate formattedYesterdaysDate;

    @Value("${openExchangeRatesAppId}")
    private String appId;

    @Value("${openExchangeRatesUrl}")
    private String url;

    private String yesterdayDate;

    @BeforeEach
    void init() {
        yesterdayDate = formattedYesterdaysDate.getYesterdayDate();
    }

    @Test
    void ShouldReturnExchangeRateOnDateIfRequestIsValid() throws IOException {
        stubFor(get(urlEqualTo(url + "/historical/" + yesterdayDate + ".json?app_id=" + appId))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(read())));

        ExchangeRate exchangeRate = openExchangeRatesClient.getExchangeRateOnDate(yesterdayDate, appId);

        assertThat(exchangeRate.getRates()).isNotEmpty();
        assertThat(exchangeRate.getRates()).containsKey("USD");
    }

    @Test
    void ShouldReturnLatestExchangeRateIfRequestIsValid() throws IOException {
        stubFor(get(urlEqualTo(url + "/latest.json?app_id=" + appId))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(read())));

        ExchangeRate exchangeRate = openExchangeRatesClient.getExchangeRateOnDate(yesterdayDate, appId);

        assertThat(exchangeRate.getRates()).isNotEmpty();
        assertThat(exchangeRate.getRates()).containsKey("USD");
    }

    private String read() throws IOException {
        return IOUtils.toString(new ClassPathResource("currency.json")
                .getInputStream(), String.valueOf(StandardCharsets.UTF_8));
    }
}