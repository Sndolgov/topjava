package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
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

    public Meal creat(Meal meal) {
        log.info("creat meal");
        checkNew(meal);
        return service.creat(meal, AuthorizedUser.id());
    }

    public void delete(int id) {
        log.info("delete meal");
        service.delete(id, AuthorizedUser.id());
    }

    public Meal get(int id) {
        log.info("get meal");
        return service.get(id, AuthorizedUser.id());
    }

    public List<MealWithExceed> getAll() {
        log.info("getAll meal");
        return MealsUtil.getWithExceeded(service.getAll(AuthorizedUser.id(), LocalDate.MIN, LocalDate.MAX), AuthorizedUser.getCaloriesPerDay());
    }

    public List<MealWithExceed> getFiltredAll(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        log.info("getFiltered meal");
        return MealsUtil.getFilteredWithExceeded(service.getAll(AuthorizedUser.id(), startDate, endDate), startTime, endTime, AuthorizedUser.getCaloriesPerDay());
    }

    public void update(Meal meal, int id) {
        log.info("update meal");
        assureIdConsistent(meal, id);
        service.update(meal, AuthorizedUser.id());
    }
}