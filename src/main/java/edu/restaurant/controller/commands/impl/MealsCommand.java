package edu.restaurant.controller.commands.impl;

import edu.restaurant.datasource.entities.Order;
import edu.restaurant.datasource.entities.OrderStatus;
import edu.restaurant.datasource.entities.UserRole;
import edu.restaurant.manager.PageManager;
import edu.restaurant.service.MealService;
import edu.restaurant.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class MealsCommand {

    private final MealService mealService;
    private final OrderService orderService;

    @Autowired
    public MealsCommand(MealService mealService, OrderService orderService) {
        this.mealService = mealService;
        this.orderService = orderService;
    }

    @GetMapping("/meals")
    public String execute(HttpSession session, Model model) {
        if(session == null || session.getAttribute("id") == null)
            return PageManager.LOGIN_REDIRECT;
        model.addAttribute("list", mealService.getAll());
        return PageManager.MEALS;
    }

    @PostMapping("/meals")
    public String post(@RequestParam int mealId, HttpSession session, Model model) throws SQLException {
        boolean isAdmin = UserRole.ADMINISTRATOR.equals(session.getAttribute("role"));
        if(!isAdmin) {
            int userId = (Integer)session.getAttribute("id");
            Order order = new Order(mealId, userId, OrderStatus.PREPARING);
            orderService.createOrder(order);
            return PageManager.ORDERS_REDIRECT;
        }
        model.addAttribute("list", mealService.getAll());
        return PageManager.MEALS;
    }

}
