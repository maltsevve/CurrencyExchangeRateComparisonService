package com.maltsevve.currencyexchangeratecomparisonservice.util;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Component
public class FormattedYesterdaysDate {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public String getYesterdayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);

        return dateFormat.format(calendar.getTime());
    }
}
