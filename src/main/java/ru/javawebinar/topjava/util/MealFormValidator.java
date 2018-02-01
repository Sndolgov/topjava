package ru.javawebinar.topjava.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import static ru.javawebinar.topjava.util.ValidationUtil.isCanSaveTime;

/**
 * Created by Сергей on 27.01.2018.
 */
@Component
public class MealFormValidator implements Validator {
    @Autowired
    MealService mealService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Meal.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Meal meal = (Meal) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateTime", "NotEmpty.userForm.dateTime");

        if (!isCanSaveTime(meal, mealService))
            errors.rejectValue("dateTime", "Exists.mealForm.dateTime", new String[]{"Дата/Время","Date/Time"}, "Второй аргумент");
    }
}
