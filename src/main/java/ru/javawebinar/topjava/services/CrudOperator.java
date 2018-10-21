package ru.javawebinar.topjava.services;

public interface CrudOperator<T> {

    void create(T obj);

    T read(long uid);

    void update(long uid, T obj);

    T delete(long uid);
}
