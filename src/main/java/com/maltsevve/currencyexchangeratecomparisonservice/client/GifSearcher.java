package com.maltsevve.currencyexchangeratecomparisonservice.client;

import com.maltsevve.currencyexchangeratecomparisonservice.model.SearchedGif;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "giphySearch", url = "${giphyUrl}")
public interface GifSearcher {
    @RequestMapping(value = "/search?limit=1&rating=g&lang=en", params = {"api_key", "q", "offset"},
            method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    SearchedGif getData(@RequestParam(value = "api_key") String apiKey, @RequestParam(value = "q") String richOrBroke,
                        @RequestParam(value = "offset") Integer offset);
}


