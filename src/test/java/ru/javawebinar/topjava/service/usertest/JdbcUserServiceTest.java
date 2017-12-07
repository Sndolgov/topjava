package ru.javawebinar.topjava.service.usertest;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;


@ActiveProfiles(Profiles.JDBC)
public class JdbcUserServiceTest extends AbstractUserServiceTest {
}