package ru.javawebinar.topjava.service.user;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.mealtest.AbstractMealServiceTest;


@ActiveProfiles(Profiles.JPA)
public class JpaUserServiceTest extends AbstractMealServiceTest {
}