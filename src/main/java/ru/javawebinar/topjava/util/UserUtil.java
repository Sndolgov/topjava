package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Сергей on 11.11.2017.
 */
public class UserUtil {
    private static final List<User> USERS = Arrays.asList(
            new User(null, "User", "user@gmail.com", "user", Role.ROLE_USER),
            new User(null, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN, Role.ROLE_USER)
    );

    public static List<User> getSortUSERS() {
        return USERS.stream()
                .sorted((o1, o2) -> {
                    if (o1.getName().compareTo(o2.getName()) != 0)
                        return o1.getName().compareTo(o2.getName());
                    else return o1.getEmail().compareTo(o2.getEmail());
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
