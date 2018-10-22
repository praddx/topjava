package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.TimePeriod;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFound;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        Meal meal = checkNotFound(repository.get(id), "id=" + id);
        if (meal.getUserId() == userId) {
            repository.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public Meal get(int id) {
        Meal meal = checkNotFound(repository.get(id), "id=" + id);
        return meal;
    }

    @Override
    public List<Meal> getAll(int userId, TimePeriod period) {
        return repository.values().stream().filter(m -> m.getUserId() == userId)
                .filter(m -> DateTimeUtil.isBetween(m.getDateTime(), period))
                .collect(Collectors.toList());
    }
}

