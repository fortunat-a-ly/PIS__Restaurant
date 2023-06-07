package edu.restaurant.controller.commands.impl;

import edu.restaurant.datasource.entities.Order;
import edu.restaurant.datasource.entities.UserRole;
import edu.restaurant.manager.PageManager;
import edu.restaurant.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
public class OrderCommand {

    private final OrderService orderService;

    @Autowired
    public OrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public String post(@RequestParam Integer orderId, HttpSession session, Model model) throws SQLException {
        if(session == null || session.getAttribute("id") == null)
            return PageManager.LOGIN_REDIRECT;

        boolean isAdmin = UserRole.ADMINISTRATOR.equals(session.getAttribute("role"));
        int userId = (int)session.getAttribute("id");

        if(isAdmin) {
            orderService.markOrderAsReady(orderId);
            model.addAttribute("list", orderService.findOrders());
        } else {
            model.addAttribute("list", orderService.findCustomerOrders(userId));
        }

        return PageManager.ORDERS;
    }

    @GetMapping("/orders")
    public String get(HttpSession session, Model model) throws SQLException {
        if(session == null || session.getAttribute("id") == null)
            return PageManager.LOGIN_REDIRECT;

        boolean isAdmin = UserRole.ADMINISTRATOR.equals(session.getAttribute("role"));
        int userId = (int)session.getAttribute("id");

        if(isAdmin) {
            model.addAttribute("list", orderService.findOrders());
        } else {
            model.addAttribute("list", orderService.findCustomerOrders(userId));
        }

        return PageManager.ORDERS;
    }
}
