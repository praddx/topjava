package ru.javawebinar.topjava.mocks;

import ru.javawebinar.topjava.Environment;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.stream.Collectors;

public class MocksDB {

    public static List<Meal> getMemoryMeals() {
       return Environment.mealStorage.values().stream().collect(Collectors.toList());
    }
}
