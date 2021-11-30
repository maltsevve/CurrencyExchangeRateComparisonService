package com.maltsevve.currencyexchangeratecomparisonservice.domain;

import com.maltsevve.currencyexchangeratecomparisonservice.model.Gif;
import com.maltsevve.currencyexchangeratecomparisonservice.model.Image;
import com.maltsevve.currencyexchangeratecomparisonservice.model.SearchedGif;

import java.util.HashMap;
import java.util.Map;

public class GiphyServiceTestData {
    public static SearchedGif searchedGif() {
        Map<String, String> original = new HashMap<>();
        original.put("url", "https://media2.giphy.com/media/LdOyjZ7io5Msw/" +
                "giphy.gif?cid=dc38547eko9apg6jkwv5k0fy6y34xrb381indxkmypopwulm&rid=giphy.gif&ct=g");

        Image image = new Image();
        image.setOriginal(original);

        Gif gif = new Gif();
        gif.setId("1");
        gif.setImages(image);
        Gif[] gifs = new Gif[]{gif};

        SearchedGif searchedGif = new SearchedGif();
        searchedGif.setData(gifs);

        return searchedGif;
    }
}
