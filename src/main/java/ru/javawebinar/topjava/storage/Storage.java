package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

public interface Storage {
    void save(Meal m);

    Meal get(String ID);

    void update(Meal m);

    void delete(String ID);

    int size();
}
