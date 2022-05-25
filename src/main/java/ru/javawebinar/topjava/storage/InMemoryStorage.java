package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

public class InMemoryStorage implements Storage{
    @Override
    public void save(Meal m) {
        MealsUtil.mealsMap.putIfAbsent(m.getId(), m);
    }

    @Override
    public Meal get(String ID) {
        return MealsUtil.mealsMap.get(ID);
    }

    @Override
    public void update(Meal m) {
        MealsUtil.mealsMap.put(m.getId(), m);
    }

    @Override
    public void delete(String ID) {
        MealsUtil.mealsMap.remove(ID);
    }

    @Override
    public int size() {
        return MealsUtil.mealsMap.size();
    }
}
