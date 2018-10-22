package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.TimePeriod;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

import java.util.List;

@Controller
public class MealRestController {

    private final MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal get(int id) {
        Meal meal = service.get(id);
        if (meal.getUserId() != authUserId()) {
            throw new IllegalArgumentException(meal + " must be with userId " + authUserId());
        }
        return meal;
    }

    public Meal create(Meal meal) {
        checkNew(meal);
        meal.setUserId(authUserId());
        return service.create(meal);
    }

    public void update(Meal meal) {
        if (meal.isNew()) {
            meal.setUserId(authUserId());
        }
        if (meal.getUserId() != authUserId()) {
            throw new IllegalArgumentException(meal + " must be with userId " + authUserId());
        }
        service.update(meal);
    }

    public void delete(int id) {
        service.delete(id, authUserId());
    }

    public List<Meal> getAll(int userId, TimePeriod period) {
        return service.getAll(userId, period);
    }


}