package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.db.MealDataBaseImpl;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Диман on 19.07.2017.
 */
public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    MealDataBaseImpl repository = MealDataBaseImpl.repository;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            if (action.equals("delete")) {
                log.debug("meal delete");
                int id = Integer.parseInt(request.getParameter("id"));
                repository.delete(id);

            } else if (action.equals("create")) {
                log.debug("meal create");
                Clock clock = Clock.systemDefaultZone();

                request.setAttribute("meal", new Meal(0, LocalDateTime.now(clock), "", 0));
                request.getRequestDispatcher("/meal.jsp").forward(request, response);

            } else if (action.equals("update")) {
                log.debug("meal update");
                int id = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("meal", repository.getById(id));
                request.getRequestDispatcher("/meal.jsp").forward(request, response);
            }
        }
        log.debug("get all meals");
        request.setAttribute("mealList", MealsUtil.getFilteredWithExceeded(repository.getMealList(), LocalTime.MIN, LocalTime.MAX, 2000));
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        log.debug("change mealList");
        int id = Integer.parseInt(request.getParameter("id"));
        if (id==0)
            id = repository.getNewId();
        String date = request.getParameter("dateTime").replace('T',' ').substring(0, 16);
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        Meal meal = new Meal(id, dateTime, description, calories);
        repository.update(meal);

        request.setAttribute("mealList", MealsUtil.getFilteredWithExceeded(repository.getMealList(), LocalTime.MIN, LocalTime.MAX, 2000));
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
