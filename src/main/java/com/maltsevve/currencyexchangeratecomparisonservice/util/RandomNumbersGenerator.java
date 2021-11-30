package com.maltsevve.currencyexchangeratecomparisonservice.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomNumbersGenerator {
    public int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(100);
    }
}
