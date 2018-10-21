package ru.javawebinar.topjava.services;

import ru.javawebinar.topjava.model.Meal;


import static ru.javawebinar.topjava.Environment.mealStorage;

public class mealCrudOperatorMemoryImpl implements CrudOperator<Meal> {
    @Override
    public void create(Meal obj) {
        mealStorage.put(obj.getUid(), obj);
    }

    @Override
    public Meal read(long uid) {
        return mealStorage.get(uid);
    }

    @Override
    public void update(long uid, Meal obj) {
        mealStorage.put(uid, obj);
    }

    @Override
    public Meal delete(long uid) {
        return mealStorage.remove(uid);
    }
}
