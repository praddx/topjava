package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class MealTestData {


    public static final Meal USER_MEAL_1 = new Meal(100002, LocalDateTime.of(2018, 10, 22,
            7, 0, 0), "Завтрак", 500);
    public static final Meal USER_MEAL_2 = new Meal(100003, LocalDateTime.of(2018, 10, 22,
            14, 0, 0), "Обед", 1000);
    public static final Meal USER_MEAL_3 = new Meal(100004, LocalDateTime.of(2018, 10, 22,
            19, 0, 0), "Ужин", 500);
    public static final Meal USER_MEAL_4 = new Meal(100005, LocalDateTime.of(2018, 10, 23,
            7, 0, 0), "Завтрак", 500);
    public static final Meal USER_MEAL_5 = new Meal(100006, LocalDateTime.of(2018, 10, 23,
            14, 0, 0), "Обед", 1000);
    public static final Meal USER_MEAL_6 = new Meal(100007, LocalDateTime.of(2018, 10, 23,
            19, 0, 0), "Ужин", 500);

    public static final Meal ADMIN_MEAL_1 = new Meal(100008, LocalDateTime.of(2018, 10, 22,
            7, 0, 0), "Завтрак", 600);
    public static final Meal ADMIN_MEAL_2 = new Meal(100009, LocalDateTime.of(2018, 10, 22,
            14, 0, 0), "Обед", 1000);
    public static final Meal ADMIN_MEAL_3 = new Meal(100010, LocalDateTime.of(2018, 10, 22,
            19, 0, 0), "Ужин", 600);
    public static final Meal ADMIN_MEAL_4 = new Meal(100011, LocalDateTime.of(2018, 10, 23,
            7, 0, 0), "Завтрак", 450);
    public static final Meal ADMIN_MEAL_5 = new Meal(100012, LocalDateTime.of(2018, 10, 23,
            14, 0, 0), "Обед", 700);
    public static final Meal ADMIN_MEAL_6 = new Meal(100013, LocalDateTime.of(2018, 10, 23,
            19, 0, 0), "Ужин", 750);

    public static List<Meal> userMeal = new ArrayList<>();

    public static List<Meal> adminMeal = new ArrayList<>();

    static {
        userMeal.add(USER_MEAL_1);
        userMeal.add(USER_MEAL_2);
        userMeal.add(USER_MEAL_3);
        userMeal.add(USER_MEAL_4);
        userMeal.add(USER_MEAL_5);
        userMeal.add(USER_MEAL_6);

        adminMeal.add(ADMIN_MEAL_1);
        adminMeal.add(ADMIN_MEAL_2);
        adminMeal.add(ADMIN_MEAL_3);
        adminMeal.add(ADMIN_MEAL_4);
        adminMeal.add(ADMIN_MEAL_5);
        adminMeal.add(ADMIN_MEAL_6);
    }

    public static void assertMatchMeal(Meal actual, Meal expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatchMeals(Iterable<Meal> actual, Meal... meals) {
        assertMatchMeals(actual, Arrays.asList(meals));
    }

    public static void assertMatchMeals(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }


}


































