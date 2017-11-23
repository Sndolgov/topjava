package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    MealService service;

    @Test
    public void get() throws Exception {
        Meal meal=service.get(START_SEQ+8, ADMIN_ID);
        assertMatchM(MEAL7,meal);
    }

    @Test (expected = NotFoundException.class)
    public void notFoundGet() throws Exception {
       service.get(MEAL1.getId(), ADMIN_ID);
    }

    @Test (expected = NotFoundException.class)
    public void notFoundGet2() throws Exception {
      service.get(START_SEQ+8, USER_ID);
    }


    @Test
    public void delete() throws Exception {
        service.delete(MEAL1.getId(), USER_ID);
        assertMatchM(service.getAll(USER_ID),  MEAL6,MEAL5,MEAL4,MEAL3,MEAL2);
    }

    @Test (expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        service.delete(MEAL1.getId(), ADMIN_ID);
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        Collection<Meal> userFilter=service.getBetweenDates(LocalDate.of(2015, Month.MAY, 30),
                LocalDate.of(2015, Month.MAY, 30), USER_ID);
        assertMatchM(Arrays.asList(MEAL3,MEAL2,MEAL1),userFilter);
    }

    @Test
    public void getAll() throws Exception {
        Collection<Meal> userAll=service.getAll(USER_ID);
       assertMatchM(Arrays.asList(MEAL6,MEAL5,MEAL4,MEAL3,MEAL2,MEAL1),userAll);
    }
    @Test (expected = AssertionError.class)
    public void getAllAssertionError() throws Exception {
        Collection<Meal> userAll=service.getAll(USER_ID);
        assertMatchM(Arrays.asList(MEAL6,MEAL5,MEAL4,MEAL3,MEAL2),userAll);
    }

    @Test
    public void update() throws Exception {
        Meal meal = new Meal(MEAL1);
        meal.setDescription(MEAL2.getDescription());
        meal.setDateTime(MEAL2.getDateTime());
        service.update(meal, USER_ID);
        assertMatchM(meal, service.get(meal.getId(), USER_ID));
    }

    @Test (expected = NotFoundException.class)
    public void updateNotFound() throws Exception {
        service.update(MEAL1, ADMIN_ID);
    }

    @Test (expected = AssertionError.class)
    public void updateAssertionError() throws Exception {
        Meal meal = new Meal(MEAL1);
        meal.setDescription(MEAL2.getDescription());
        meal.setDateTime(MEAL2.getDateTime());
        service.update(meal, USER_ID);
        assertMatchM(MEAL1, service.get(MEAL1.getId(), USER_ID));
    }

    @Test
    public void create() throws Exception {
        Meal newMeal=new Meal(LocalDateTime.of(2015, Month.JUNE, 5, 23, 0), "Админ новый ужин", 150);
        Meal createdMeal=service.create(newMeal, ADMIN_ID);
        assertMatchM(service.getAll(ADMIN_ID), createdMeal, MEAL8, MEAL7);
    }

}