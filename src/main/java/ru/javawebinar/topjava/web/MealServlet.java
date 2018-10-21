package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.mocks.MocksDB;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MealServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       List<Meal> meals = MocksDB.getMeals();
       List<MealWithExceed> exceedMeals = MealsUtil.getFilteredWithExceeded(meals, 2000);
       request.setAttribute("meals", exceedMeals);
       request.setAttribute("dateTimeFormatter", DateTimeFormatter.ofPattern("dd-MM-yyy hh:mm"));
       request.getRequestDispatcher("meals.jsp").forward(request, response);
    }

}
