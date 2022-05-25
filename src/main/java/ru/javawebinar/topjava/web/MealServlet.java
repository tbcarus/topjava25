package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.storage.InMemoryStorage;
import ru.javawebinar.topjava.storage.Storage;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static final Storage storage = new InMemoryStorage();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("delete".equals(request.getParameter("action"))) {
            String id = request.getParameter("id");
            log.debug("delete meal " + id);
            storage.delete(id);
        }

        log.debug("redirect to meals");
        List<MealTo> mealsTo = MealsUtil.filteredByStreams(new ArrayList<>(MealsUtil.mealsMap.values()), LocalTime.MIN, LocalTime.MAX, MealsUtil.CALORIES_PER_DAY);
        request.setAttribute("mealsToList", mealsTo);

        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
