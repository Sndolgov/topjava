package ru.javawebinar.topjava.service.usertest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.service.mealtest.AbstractMealServiceTest;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Arrays;

import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL1;
import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL2;
import static ru.javawebinar.topjava.MealTestData.MEAL1_ID;
import static ru.javawebinar.topjava.UserTestData.*;


@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserServiceTest extends AbstractMealServiceTest {
    @Autowired
    private UserService service;

    @Test
    public void testGetWithMeal(){
        User user = service.getWithMeal(ADMIN_ID);
        assertMatch(user, ADMIN);
        MealTestData.assertMatch(user.getMeals(), Arrays.asList(ADMIN_MEAL1, ADMIN_MEAL2));
    }

    @Test (expected = NotFoundException.class)
    public void testGetWithMealNotFound(){
        service.getWithMeal(MEAL1_ID);
    }
}