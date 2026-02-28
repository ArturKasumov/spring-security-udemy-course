package com.arturk.utils;

import lombok.experimental.UtilityClass;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class TimeUtils {

    public static String currentTimeAsString() {
        OffsetDateTime nowUtc = OffsetDateTime.now(java.time.ZoneOffset.UTC);
        return nowUtc.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }
}
