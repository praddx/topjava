package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.TimePeriod;

import java.util.Collection;
import java.util.List;

public interface MealRepository {

    Meal save(Meal meal);

    boolean delete(int id, int userId);

    Meal get(int id);

    List<Meal> getAll(int userId, TimePeriod period);
}
