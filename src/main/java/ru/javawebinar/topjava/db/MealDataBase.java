package ru.javawebinar.topjava.db;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Сергей on 05.11.2017.
 */
public interface MealDataBase {
    List<Meal> getMealList();
    void delete(int id);
    Meal getById(int id);
    void update (Meal meal);
    int getNewId();
}
