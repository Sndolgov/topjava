package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

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
    }

    @Test
    public void delete() throws Exception {
        service.delete(100002, 100000);
        System.out.println(service.getAll(USER_ID).size());
        System.out.println(service.get(100003, USER_ID));

        System.out.println(service.getAll(USER_ID));
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
    }

    @Test
    public void getAll() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void create() throws Exception {
        Meal meal = new Meal( LocalDateTime.of(2017, Month.MAY, 31, 20, 0), "Ужин", 510);
        System.out.println(service.getAll(ADMIN_ID).size());
        service.create(meal, 100001);
        System.out.println(service.getAll(ADMIN_ID).size());
    }

}