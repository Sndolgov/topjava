package ru.javawebinar.topjava.service.usertest;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.mealtest.AbstractMealServiceTest;


@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserServiceTest extends AbstractMealServiceTest {
}