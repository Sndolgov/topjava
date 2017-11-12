package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {
    private final MealService service;
    protected final Logger log = LoggerFactory.getLogger(getClass());


    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal creat(Meal meal, int userId) {
        log.info("creat meal");
        checkNew(meal);
        return service.creat(meal, userId);
    }

    public void delete(int id, int userId) {
        log.info("delete meal");
        service.delete(id, userId);
    }

    public Meal get(int id, int userId) {
        log.info("get meal");
        return service.get(id, userId);
    }

    public List<Meal> getAll(int userId) {
        log.info("getAll meal");
        return (List<Meal>) service.getAll(userId);
    }

    public void update(Meal meal, int id, int userId) {
        log.info("update meal");
        assureIdConsistent(meal, id);
        service.update(meal, userId);
    }
}