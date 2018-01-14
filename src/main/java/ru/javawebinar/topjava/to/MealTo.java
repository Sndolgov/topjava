package ru.javawebinar.topjava.to;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Сергей on 14.01.2018.
 */
public class MealTo {
    Integer id;
    String dateTime, description;
    Integer calories;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }



    @Override
    public String toString() {
        return "MealTo{" +
                "id=" + id +
                ", dateTime='" + dateTime + '\'' +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
