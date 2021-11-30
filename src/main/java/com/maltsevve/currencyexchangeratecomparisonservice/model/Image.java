package com.maltsevve.currencyexchangeratecomparisonservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {
    Map<String, String> original;
}
