package com.maltsevve.currencyexchangeratecomparisonservice;

import com.maltsevve.currencyexchangeratecomparisonservice.rest.RestControllerV1;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CurrencyExchangeRateComparisonServiceApplicationTests {
    @Autowired
    private RestControllerV1 restControllerV1;

    @Test
    void contextLoads() {
        assertThat(restControllerV1).isNotNull();
    }
}
