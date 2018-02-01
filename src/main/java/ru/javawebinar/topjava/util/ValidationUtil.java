package ru.javawebinar.topjava.util;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.HasId;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.to.UserTo;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.StringJoiner;

public class ValidationUtil {

    private ValidationUtil() {
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalArgumentException(bean + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(HasId bean, int id) {
//      http://stackoverflow.com/a/32728226/548473
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.getId() != id) {
            throw new IllegalArgumentException(bean + " must be with id=" + id);
        }
    }

    //  http://stackoverflow.com/a/28565320/548473
    public static Throwable getRootCause(Throwable t) {
        Throwable result = t;
        Throwable cause;

        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }
        return result;
    }



    public static boolean isCanChange(UserTo userTo, UserService userService) {
        Integer userToId = userTo.getId();
        Integer userId = null;
        try {
            userId = userService.getByEmail(userTo.getEmail()).getId();
        } catch (Exception ignored) {
        }

        if (userToId != null) {
            if (!userToId.equals(userId))
                return false;
        } else if (userId != null)
            return false;
        return true;
    }

    public static boolean isCanSaveTime(Meal validateMeal, MealService mealService) {
        if (validateMeal.getDateTime()!=null) {
            LocalDateTime ldt = validateMeal.getDateTime();
            List<Meal> meals = mealService.getAll(AuthorizedUser.id());

            for (Meal meal : meals) {
                if (ldt.equals(meal.getDateTime())) {
                    if (validateMeal.getId() == null || !meal.getId().equals(validateMeal.getId()))
                        return false;
                }
            }
        }
        return true;
    }
}