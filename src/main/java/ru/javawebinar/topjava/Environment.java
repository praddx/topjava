package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

public class Environment {

    public static final Map<Long, Meal> mealStorage = new HashMap<>();

    public static long memoryMealUidCount = 0;

    static {
        Meal meal = new Meal(MealsUtil.generateMemoryMealUid(), LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
        mealStorage.put(meal.getUid(), meal);
        meal = new Meal(MealsUtil.generateMemoryMealUid(), LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
        mealStorage.put(meal.getUid(), meal);
        meal = new Meal(MealsUtil.generateMemoryMealUid(), LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
        mealStorage.put(meal.getUid(), meal);
        meal = new Meal(MealsUtil.generateMemoryMealUid(), LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000);
        mealStorage.put(meal.getUid(), meal);
        meal = new Meal(MealsUtil.generateMemoryMealUid(), LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500);
        mealStorage.put(meal.getUid(), meal);
        meal = new Meal(MealsUtil.generateMemoryMealUid(), LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);
        mealStorage.put(meal.getUid(), meal);
    }

}
