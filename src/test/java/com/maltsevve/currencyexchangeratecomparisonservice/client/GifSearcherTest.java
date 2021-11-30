package com.maltsevve.currencyexchangeratecomparisonservice.client;

import com.maltsevve.currencyexchangeratecomparisonservice.model.Gif;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 9091)
class GifSearcherTest {
    @Autowired
    private GifSearcher gifSearcher;

    @Value("${giphyApiKey}")
    private String apiKey;

    @Value("${giphyUrl}")
    private String giphyUrl;

    @Test
    void shouldReturnSearcherGifIfRequestIsValid() throws IOException{
        stubFor(get(urlEqualTo(giphyUrl + "/search?limit=1&rating=g&lang=en&api_key=" + apiKey + "&q=rich&offset=1"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(read())));

        Gif[] gifs = gifSearcher.getData(apiKey, "rich", 1).getData();
        Gif gif = gifs[0];

        assertThat(gifs.length).isEqualTo(1);
        assertThat(gif.getId()).isEqualTo("lptjRBxFKCJmFoibP3");
        assertThat(gif.getImages().getOriginal().get("hash")).isEqualTo("c954d468a9486db13c102d7e52b9b4ce");
    }

    private String read() throws IOException {
        return IOUtils.toString(new ClassPathResource("gif.json")
                .getInputStream(), String.valueOf(StandardCharsets.UTF_8));
    }
}