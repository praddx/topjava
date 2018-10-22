package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }

    public static boolean isBetween(LocalDateTime dateTime, TimePeriod period) {
        return dateTime.toLocalDate().compareTo(period.getStartDate()) >= 0
                && dateTime.toLocalDate().compareTo(period.getEndDate()) <= 0
                && dateTime.toLocalTime().compareTo(period.startTime) >= 0
                && dateTime.toLocalTime().compareTo(period.endTime) <= 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}
