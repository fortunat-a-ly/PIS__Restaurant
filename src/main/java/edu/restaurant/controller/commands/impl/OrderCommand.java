package edu.restaurant.controller.commands.impl;

import edu.restaurant.controller.commands.Command;
import edu.restaurant.datasource.entities.UserRole;
import edu.restaurant.manager.PageManager;
import edu.restaurant.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OrderCommand implements Command {

    private static final OrderService orderService = new OrderService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("id") == null)
            return PageManager.LOGIN_REDIRECT;

        boolean isAdmin = UserRole.ADMINISTRATOR.equals(session.getAttribute("role"));
        int userId = (int)session.getAttribute("id");

        if (request.getMethod().equals("POST") && isAdmin) {
            int orderId = Integer.parseInt(request.getParameter("order_id"));
            orderService.markOrderAsReady(orderId);
        }

        if(isAdmin) {
            request.setAttribute("list", orderService.findOrders());
        } else {
            request.setAttribute("list", orderService.findCustomerOrders(userId));
        }

        return PageManager.ORDERS;
    }

}
