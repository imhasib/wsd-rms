package com.hsb.rms.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class DateUtils {
    public static Instant startOfTheCurrentDay() {
        return getStartOfDay(Instant.now().atZone(ZoneId.systemDefault())).toInstant();
    }

    public static Instant endOfTheCurrentDay() {
        return getEndOfDay(Instant.now().atZone(ZoneId.systemDefault())).toInstant();
    }

    public static ZonedDateTime getStartOfDay(ZonedDateTime date){
        return date.truncatedTo(ChronoUnit.DAYS); // Start of the day
    }
    public static ZonedDateTime getEndOfDay(ZonedDateTime date){
        return date.truncatedTo(ChronoUnit.DAYS).plusDays(1).minusNanos(1); // End of the day
    }
}
