package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Сергей on 17.12.2017.
 */

@Controller
@RequestMapping("/meals")
public class JspMealController extends MealRestController {

    @Autowired
    private MealService service;

    public JspMealController(MealService service) {
        super(service);
    }

    @GetMapping()
    public String meals(Model model) {
        List<MealWithExceed> meals = MealsUtil.getWithExceeded(service.getAll(AuthorizedUser.id()), AuthorizedUser.getCaloriesPerDay());
        model.addAttribute("meals", meals);
        return "meals";
    }

    @RequestMapping(params = "action=delete")
    public String delete(HttpServletRequest request) {
        int mealId = Integer.parseInt(request.getParameter("id"));
        service.delete(mealId, AuthorizedUser.id());
        return "redirect:/meals";
    }

    @RequestMapping(params = "action=update")
    public String update(HttpServletRequest request, Model model) {
        int mealId = Integer.parseInt(request.getParameter("id"));
        Meal meal = service.get(mealId, AuthorizedUser.id());
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @RequestMapping(params = "action=create")
    public String creat(Model model) {
        model.addAttribute("meal", new Meal(LocalDateTime.now(),"",0));
        return "mealForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createOrUpdate(HttpServletRequest request)  {
        System.out.println(request.getCharacterEncoding());
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));
        String id = request.getParameter("id");
        if (id.isEmpty()) {
            service.create(meal, AuthorizedUser.id());
        } else {
            meal.setId(Integer.parseInt(id));
            service.update(meal, AuthorizedUser.id());
        }
        return "redirect:meals";
    }


    @RequestMapping(params = "action=filter")
    public String filter (HttpServletRequest request, Model model){
        LocalDate startDate = DateTimeUtil.parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = DateTimeUtil.parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = DateTimeUtil.parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = DateTimeUtil.parseLocalTime(request.getParameter("endTime"));

        List <MealWithExceed> meals=getBetween(startDate,startTime, endDate, endTime);

        model.addAttribute("meals", meals);
        return "meals";
    }


}
