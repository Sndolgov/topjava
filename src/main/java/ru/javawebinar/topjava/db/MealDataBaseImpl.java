package ru.javawebinar.topjava.db;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Сергей on 04.11.2017.
 */
public class MealDataBaseImpl implements MealDataBase {

    //   AtomicInteger id = new AtomicInteger(1);
    private Map<Integer, Meal> mealMap = new ConcurrentHashMap<>();
    private static AtomicInteger idCount = new AtomicInteger(1);


    public static final MealDataBaseImpl repository = new MealDataBaseImpl();

    private MealDataBaseImpl() {
        mealMap.put(idCount.get(), new Meal(idCount.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        mealMap.put(idCount.get(), new Meal(idCount.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        mealMap.put(idCount.get(), new Meal(idCount.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        mealMap.put(idCount.get(), new Meal(idCount.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        mealMap.put(idCount.get(), new Meal(idCount.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        mealMap.put(idCount.get(), new Meal(idCount.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));

    }


    @Override
    public List<Meal> getMealList() {
        return new ArrayList<>(mealMap.values());
    }

    @Override
    public void delete(int id) {
        mealMap.remove(id);
    }


    @Override
    public Meal getById(int id) {
        return mealMap.get(id);
    }


    @Override
    public void update(Meal meal) {
        mealMap.put(meal.getId(), meal);
    }

    @Override
    public int getNewId() {
        return idCount.getAndIncrement();
    }
}
