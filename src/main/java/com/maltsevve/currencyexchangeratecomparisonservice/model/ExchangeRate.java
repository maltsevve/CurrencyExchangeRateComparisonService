package com.maltsevve.currencyexchangeratecomparisonservice.model;

import lombok.Data;

import java.util.Map;

@Data
public class ExchangeRate {
    private String disclaimer;
    private String license;
    private long timestamp;
    private String base;
    private Map<String, Double> rates;
}
