package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Meal {

    private final long uid;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    public Meal(long uid, LocalDateTime dateTime, String description, int calories) {
        this.uid = uid;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public long getUid() {
        return uid;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }
}
