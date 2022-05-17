package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with excess. Implement by cycles
        List<UserMeal> userMealsFiltered = new ArrayList<>();
        List<UserMealWithExcess> mealWithExcessesList = new ArrayList<>();
        Map<LocalDate, Integer> sumMapByDate = new HashMap<>();
        for (UserMeal meal : meals) {
            int calories = sumMapByDate.get(meal.getDateTime().toLocalDate()) == null ? 0 : sumMapByDate.get(meal.getDateTime().toLocalDate());
            sumMapByDate.put(meal.getDateTime().toLocalDate(), calories + meal.getCalories());
            if (TimeUtil.isBetweenHalfOpen(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                userMealsFiltered.add(meal);
            }
        }
        for (UserMeal meal : userMealsFiltered) {
            if (sumMapByDate.get(meal.getDateTime().toLocalDate()) > caloriesPerDay) {
                mealWithExcessesList.add(new UserMealWithExcess(meal, true));
            } else {
                mealWithExcessesList.add(new UserMealWithExcess(meal, false));
            }
        }
        return mealWithExcessesList;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> sumCaloriesPerDay = meals.stream().collect(Collectors.groupingBy(m -> m.getLocalDate(), Collectors.summingInt(m -> m.getCalories())));
        List<UserMealWithExcess> list = meals.stream()
                .filter(m -> TimeUtil.isBetweenHalfOpen(m.getLocalTime(), startTime, endTime))
                .map(m -> new UserMealWithExcess(m, sumCaloriesPerDay.get(m.getLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
        return list;
    }
}