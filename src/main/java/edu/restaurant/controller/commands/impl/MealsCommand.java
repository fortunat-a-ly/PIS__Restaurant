package edu.restaurant.controller.commands.impl;

import edu.restaurant.controller.commands.Command;
import edu.restaurant.datasource.entities.Order;
import edu.restaurant.datasource.entities.OrderStatus;
import edu.restaurant.datasource.entities.UserRole;
import edu.restaurant.manager.PageManager;
import edu.restaurant.service.MealService;
import edu.restaurant.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MealsCommand implements Command {

    private static final MealService mealService = new MealService();
    private static final OrderService orderService = new OrderService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if(session.getAttribute("email") == null)
            return PageManager.LOGIN;

        boolean isAdmin = UserRole.ADMINISTRATOR.equals(session.getAttribute("role"));

        if (request.getMethod().equals("POST") && !isAdmin) {
            int mealId = Integer.parseInt(request.getParameter("order_id"));
            int userId = Integer.parseInt((String)session.getAttribute("user_id"));
            Order order = new Order(mealId, userId, OrderStatus.PREPARING);
            orderService.createOrder(order);
           return PageManager.ORDERS;
        }

        request.setAttribute("list", mealService.getAll());
        return PageManager.MEALS;
    }

}
