package com.maltsevve.currencyexchangeratecomparisonservice.service;

import com.maltsevve.currencyexchangeratecomparisonservice.client.GifSearcher;
import com.maltsevve.currencyexchangeratecomparisonservice.domain.GiphyServiceTestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
class GiphyServiceTest {
    @Autowired
    GiphyService giphyService;

    @MockBean
    GifSearcher gifSearcher;

    @Value("${giphyApiKey}")
    private String apiKey;

    @Test
    void shouldReturnUrlString() {
        given(gifSearcher.getData(anyString(), eq("rich"), anyInt()))
                .willReturn(GiphyServiceTestData.searchedGif());

        String url = giphyService.getRandomGif(true);

        assertFalse(url.isEmpty());
    }
}