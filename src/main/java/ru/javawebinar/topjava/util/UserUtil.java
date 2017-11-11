package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Сергей on 11.11.2017.
 */
public class UserUtil {
    private static final List<User> USERS = Arrays.asList(
            new User(null, "User", "user@gmail.com", "user", Role.ROLE_USER),
            new User(null, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN, Role.ROLE_USER)
            );

    public static List<User> getSortUSERS() {
        Collections.sort(USERS, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return USERS;
    }


}
