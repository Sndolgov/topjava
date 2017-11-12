package ru.javawebinar.topjava.repository.mock;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal ->
                {
                    if (meal.isNew())
                        meal.setId(counter.incrementAndGet());
                    repository.put(meal.getId(), meal);
                }
        );
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(userId);
            repository.put(meal.getId(), meal);
            return meal;
        } else if (meal.getUserId() == userId) {
            repository.put(meal.getId(), meal);
            return meal;
        }
        return null;

    }

    @Override
    public boolean delete(int id, int userId) {
        if (repository.get(id) != null && repository.get(id).getUserId() == userId) {
            repository.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        if (repository.get(id) != null && repository.get(id).getUserId() == userId)
            return repository.get(id);
        else return null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.values().stream()
                .filter(meal -> meal.getUserId()==userId)
                .sorted(Comparator.comparing(Meal::getDateTime))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static void main(String[] args) {
        InMemoryMealRepositoryImpl inMemoryMealRepository = new InMemoryMealRepositoryImpl();
//        System.out.println("size: " + inMemoryMealRepository.repository.size());
//        inMemoryMealRepository.repository.values().forEach(meal -> System.out.println(meal.getId() + " " + meal.getUserId()));
//        inMemoryMealRepository.delete(7, 2);
//        System.out.println("size: " + inMemoryMealRepository.repository.size());
//        inMemoryMealRepository.repository.values().forEach(meal -> System.out.println(meal.getId() + " " + meal.getUserId()));
        System.out.println(inMemoryMealRepository.getAll(1));


    }


}


