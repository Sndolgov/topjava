package ru.javawebinar.topjava.service.mealtest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static ru.javawebinar.topjava.MealTestData.MEAL1;
import static ru.javawebinar.topjava.MealTestData.MEAL1_ID;
import static ru.javawebinar.topjava.MealTestData.assertMatch;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.UserTestData.USER_ID;


@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaMealServiceTest extends AbstractMealServiceTest {

    @Autowired
    MealService mealService;

    @Test
    public void testGetWithUser(){
        Meal meal = mealService.getWithUser(MEAL1_ID);
        assertMatch(meal, MEAL1);
        UserTestData.assertMatch(meal.getUser(), USER);
    }

    @Test (expected = NotFoundException.class)
    public void testGetWithUserNotFound(){
       mealService.getWithUser(USER_ID);
    }

}