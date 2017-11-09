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
    private MealDataBaseImpl repository =  MealDataBaseImpl.repository;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "delete": {
                    log.debug("meal delete");
                    repository.delete(Integer.parseInt(request.getParameter("id")));
                    response.sendRedirect("meals");
                    break;
                }
                case "create":
                    log.debug("meal create");
                    request.setAttribute("meal", new Meal(LocalDateTime.now(Clock.systemDefaultZone()), "", 0));
                    request.getRequestDispatcher("/meal.jsp").forward(request, response);
                    break;
                case "update": {
                    log.debug("meal update");
                    request.setAttribute("meal", repository.getById(Integer.parseInt(request.getParameter("id"))));
                    request.getRequestDispatcher("/meal.jsp").forward(request, response);
                    break;
                }
            }
        }
        else {
            log.debug("get all meals");
            request.setAttribute("mealList", MealsUtil.getFilteredWithExceeded(repository.getList(), LocalTime.MIN, LocalTime.MAX, 2000));
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        log.debug("change mealList");
        int id = Integer.parseInt(request.getParameter("id"));
        String date = request.getParameter("dateTime").replace('T',' ').substring(0, 16);
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        Meal meal = new Meal(id, dateTime, description, calories);
        if (id==0)
            repository.creat(meal);
        else
            repository.update(meal);

        request.setAttribute("mealList", MealsUtil.getFilteredWithExceeded(repository.getList(), LocalTime.MIN, LocalTime.MAX, 2000));
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
