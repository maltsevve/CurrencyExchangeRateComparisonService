package com.maltsevve.currencyexchangeratecomparisonservice.rest;

import com.maltsevve.currencyexchangeratecomparisonservice.service.GiphyService;
import com.maltsevve.currencyexchangeratecomparisonservice.service.OpenExchangeRatesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RestControllerV1Test {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OpenExchangeRatesService openExchangeRatesService;

    @MockBean
    private GiphyService giphyService;

    @MockBean
    private NullPointerException exception;

    @Test
    void shouldReturnBadRequestIfExchangeRatesComparisonThrowException() throws Exception {
        given(openExchangeRatesService.exchangeRatesComparison(any(), any())).willThrow(exception);

        ResultActions actual = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/exchange-rates/USD"));

        actual.andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestIfGifUrlIsNull() throws Exception {
        given(openExchangeRatesService.exchangeRatesComparison(any(), any())).willReturn(true);
        given(giphyService.getRandomGif(anyBoolean())).willReturn(null);

        ResultActions actual = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/exchange-rates/"));

        actual.andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnStatusFoundIfExchangeRatesComparisonAndGifUrlReturnBooleanAndUrl() throws Exception {
        given(openExchangeRatesService.exchangeRatesComparison(any(), any())).willReturn(true);
        given(giphyService.getRandomGif(true)).willReturn("https://");

        ResultActions actual = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/exchange-rates/USD"));

        actual.andExpect(status().isFound());
    }
}