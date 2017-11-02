package ru.javawebinar.topjava.util;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */

public class UserMealsUtil {

    private static List<UserMeal> mealList;

    static {
        mealList = Arrays.asList(
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
    }

    public static void main(String[] args) throws RunnerException {


        Options options = new OptionsBuilder()
                .include(UserMealsUtil.class.getSimpleName()).forks(1).build();

        new Runner(options).run();


        List<UserMealWithExceed> list = getFilteredWithExceeded2(mealList, LocalTime.of(10, 30), LocalTime.of(18, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();


      /*  for (UserMealWithExceed u : list)
            System.out.println(u);*/

    }


    @Benchmark
    public void filter() {

        getFilteredWithExceeded2(mealList, LocalTime.of(10, 30), LocalTime.of(18, 0), 2000);
    }


    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field

        Map<LocalDate, Integer> caloriesInDay = new HashMap<>();


        mealList.forEach(new Consumer<UserMeal>() {
            public void accept(UserMeal u) {
                caloriesInDay.merge(u.getDateTime().toLocalDate(), u.getCalories(), Integer::sum);
            }
        });

        List<UserMealWithExceed> listMealWithExceed = new ArrayList<>();

        mealList.forEach(new Consumer<UserMeal>() {
            public void accept(UserMeal u) {
                if (TimeUtil.isBetween(u.getDateTime().toLocalTime(), startTime, endTime)) {
                    int calories = caloriesInDay.get(u.getDateTime().toLocalDate());
                    boolean exceed = calories > caloriesPerDay;
                    listMealWithExceed.add(new UserMealWithExceed(u.getDateTime(), u.getDescription(), u.getCalories(), exceed));
                }
            }
        });

        return listMealWithExceed;
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded2(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {


        Map<LocalDate, Integer> caloriesInDay = mealList.parallelStream()
                .collect(Collectors.toMap(s-> s.getDateTime().toLocalDate(), UserMeal::getCalories, Integer::sum));

        return mealList.parallelStream()
                .filter(s -> TimeUtil.isBetween(s.getDateTime().toLocalTime(), startTime, endTime))
                .map(s -> (new UserMealWithExceed(s.getDateTime(), s.getDescription(), s.getCalories(), caloriesInDay.get(s.getDateTime().toLocalDate()) > caloriesPerDay)))
                .collect(Collectors.toList());
    }


}
