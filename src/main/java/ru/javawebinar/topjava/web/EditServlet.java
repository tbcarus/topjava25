package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.storage.InMemoryStorage;
import ru.javawebinar.topjava.storage.Storage;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class EditServlet extends HttpServlet {
    private static final Logger log = getLogger(EditServlet.class);
    private static final Storage storage = new InMemoryStorage();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        Meal m = null;
        switch (action) {
            case "edit":
                String id = request.getParameter("id");
                m = storage.get(id);
                log.debug("redirect to update meal");
                break;
            case "create":
                m = new Meal("new");
                log.debug("redirect to create meal");
                break;
        }

        request.setAttribute("meal", m);
        request.getRequestDispatcher("/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("datetime"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        if (id.equals("new")) {
            storage.save(new Meal(dateTime, description, calories));
        } else {
            storage.update(new Meal(id, dateTime, description, calories));
        }
        response.sendRedirect("../meals");
    }
}
