package ru.javawebinar.topjava.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.to.UserTo;

import static ru.javawebinar.topjava.util.UserUtil.asTo;
import static ru.javawebinar.topjava.util.ValidationUtil.isCanChange;

/**
 * Created by Сергей on 27.01.2018.
 */

@Component
public class UserFormValidator implements Validator {
    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return (User.class.equals(aClass)|| UserTo.class.equals(aClass));
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserTo userTo;
        if (o instanceof User) {
            User user = (User) o;
            userTo = asTo(user);
        }
        else userTo = (UserTo) o;

        if (!isCanChange(userTo, userService))
            errors.rejectValue("email", "пользователь с таким email уже зарегистрирован", "пользователь с таким email уже зарегистрирован");
    }
}
