package com.maltsevve.currencyexchangeratecomparisonservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Gif {
    private String id;
    private Image images;
}
