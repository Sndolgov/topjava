package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2016, Month.MAY, 30, 10, 0), "Завтрак", 1500),
                new UserMeal(LocalDateTime.of(2016, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2016, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)

        );

        List<UserMealWithExceed> list = getFilteredWithExceeded(mealList, LocalTime.of(12, 30), LocalTime.of(18, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();

        for (UserMealWithExceed u : list)
            System.out.println(u);
    }


    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field

        Map<LocalDate, Integer> caloriesInDay = new HashMap<>();
        for (UserMeal userMeal : mealList) {
            caloriesInDay.merge(userMeal.getDateTime().toLocalDate(), userMeal.getCalories(), (a, b) -> a + b);
            /*if (caloriesInDay.get(userMeal.getDateTime().toLocalDate())==null)
            caloriesInDay.put(userMeal.getDateTime().toLocalDate(), userMeal.getCalories());
            else caloriesInDay.put(userMeal.getDateTime().toLocalDate(), caloriesInDay.get(userMeal.getDateTime().toLocalDate())+userMeal.getCalories());*/
        }

        List<UserMealWithExceed> listMealWithExceed = new ArrayList<>();
        for (UserMeal userMeal : mealList) {
            if (userMeal.getDateTime().toLocalTime().isAfter(startTime) && userMeal.getDateTime().toLocalTime().isBefore(endTime)) {
                int calories = caloriesInDay.get(userMeal.getDateTime().toLocalDate());
                boolean exceed = calories > caloriesPerDay;
                listMealWithExceed.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), exceed));
            }
        }
        return listMealWithExceed;
    }
}
