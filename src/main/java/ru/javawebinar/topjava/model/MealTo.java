package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class MealTo {
    private final String ID;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final boolean excess;

    public MealTo(Meal meal, boolean excess) {
        this.ID = meal.getId();
        this.dateTime = meal.getDateTime();
        this.description = meal.getId();
        this.calories = meal.getCalories();
        this.excess = excess;
    }

    public MealTo(String ID, LocalDateTime dateTime, String description, int calories, boolean excess) {
        this.ID = ID;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
    }

    public String getID() {
        return ID;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExcess() {
        return excess;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excess=" + excess +
                '}';
    }
}
