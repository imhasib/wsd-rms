package com.hsb.rms.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class DateUtils {
    public static Instant startOfTheCurrentDay() {
        return LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
    }

    public static Instant endOfTheCurrentDay() {
        return LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).minusNanos(1).toInstant();
    }
}
