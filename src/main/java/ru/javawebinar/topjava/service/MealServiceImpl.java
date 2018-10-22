package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.TimePeriod;

import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    private final MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }


    @Override
    public Meal create(Meal meal) {
        return repository.save(meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        return repository.delete(id, userId);
    }

    @Override
    public Meal get(int id) {
        return repository.get(id);
    }

    @Override
    public void update(Meal meal) {
        repository.save(meal);
    }

    @Override
    public List<Meal> getAll(int userId, TimePeriod period) {
        return repository.getAll(userId, period);
    }
}