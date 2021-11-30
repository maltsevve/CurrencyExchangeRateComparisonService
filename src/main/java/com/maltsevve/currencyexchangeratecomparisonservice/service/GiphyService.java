package com.maltsevve.currencyexchangeratecomparisonservice.service;

import com.maltsevve.currencyexchangeratecomparisonservice.model.Gif;
import com.maltsevve.currencyexchangeratecomparisonservice.client.GifSearcher;
import com.maltsevve.currencyexchangeratecomparisonservice.model.SearchedGif;
import com.maltsevve.currencyexchangeratecomparisonservice.util.RandomNumbersGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

@Service
public class GiphyService {
    @Value("${giphyApiKey}")
    private String giphyApiKey;

    private final GifSearcher gifSearcher;
    private final RandomNumbersGenerator randomNumbersGenerator;

    public GiphyService(GifSearcher gifSearcher, RandomNumbersGenerator randomNumbersGenerator) {
        this.gifSearcher = gifSearcher;
        this.randomNumbersGenerator = randomNumbersGenerator;
    }

    public String getRandomGif(boolean richOrBroke) {
        String result = richOrBroke ? "rich" : "broke";
        SearchedGif gifData = gifSearcher.getData(giphyApiKey, result, randomNumbersGenerator.getRandomNumber());

        try {
            Gif gif = gifData.getData()[0];
            Map<String, String> images = gif.getImages().getOriginal();

            return new URL(images.get("url")).toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }
}