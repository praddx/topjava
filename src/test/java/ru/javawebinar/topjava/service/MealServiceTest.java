package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({"classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    public MealService service;

    @Test
    public void get() {
        Meal meal = service.get(USER_MEAL_1.getId(), USER_ID);
        assertMatchMeal(meal, USER_MEAL_1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(USER_MEAL_1.getId(), ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void delete() {
        service.delete(USER_MEAL_1.getId(), USER_ID);
        service.get(USER_MEAL_1.getId(), USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteAnotherUsersMeal(){
        service.delete(ADMIN_MEAL_1.getId(), USER_ID);
    }

    @Test
    public void getBetweenDateTimes() {
        List<Meal> mealsBetweenTime = service.getBetweenDateTimes(LocalDateTime.of(2018, 10, 20, 10, 0, 0),
                LocalDateTime.of(2018, 10, 30, 17, 0, 0),USER_ID);
        assertMatchMeals(mealsBetweenTime, USER_MEAL_2, USER_MEAL_5);
    }

    @Test
    public void getAll() {
        List<Meal> meals = service.getAll(USER_ID);
        assertMatchMeals(meals, userMeal);
    }

    @Test
    public void update() {
        Meal newMeal = USER_MEAL_1;
        newMeal.setDescription("Полдник");
        service.update(newMeal, USER_ID);
        assertMatchMeal(service.get(USER_MEAL_1.getId(), USER_ID), newMeal);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() {
        Meal newMeal = USER_MEAL_1;
        newMeal.setDescription("Полдник");
        service.update(newMeal, ADMIN_ID);
    }

    @Test
    public void create() {
        Meal newMeal = new Meal(LocalDateTime.of(2018, 10, 24, 16, 0, 0),
                "Полдник", 1000);
        service.create(newMeal, USER_ID);
        assertMatchMeal(service.get(100014, USER_ID), newMeal);
    }
}