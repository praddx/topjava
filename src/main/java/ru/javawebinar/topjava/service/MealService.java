package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.TimePeriod;

import java.util.List;

public interface MealService {

    Meal create(Meal meal);

    boolean delete(int id, int userId);

    Meal get(int id);

    void update(Meal meal);

    List<Meal> getAll(int userId, TimePeriod period);
}