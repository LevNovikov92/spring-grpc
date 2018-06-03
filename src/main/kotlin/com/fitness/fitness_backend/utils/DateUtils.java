package com.fitness.fitness_backend.utils;

import java.util.Date;

/**
 * Lev
 * 06.12.2016
 */
public class DateUtils {
    public static Long getUnixTimestamp(Date date) {
        return date.getTime() / 1000;
    }

    public static Long getUnixTimestamp() {
        return getUnixTimestamp(new Date());
    }
}
