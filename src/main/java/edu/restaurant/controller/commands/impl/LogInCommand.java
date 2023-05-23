package edu.restaurant.controller.commands.impl;

import edu.restaurant.controller.commands.Command;
import edu.restaurant.datasource.entities.User;
import edu.restaurant.manager.PageManager;
import edu.restaurant.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogInCommand implements Command {

    private final UserService userService = new UserService();
    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);

        // check if already logged in
        if(session.getAttribute("email") != null) {
            return PageManager.MEALS;
        }

        String email=request.getParameter("email");
        String password=request.getParameter("password");

        User user = userService.getAccountByEmail(email);
        // check if login is successful
        if(user.getEmail().equals(email) && user.getPassword().equals(password)) {
            request.getSession().setAttribute("email",email);
            request.getSession().setAttribute("id", user.getId());
            request.getSession().setAttribute("role", user.getRole());
            return PageManager.ORDERS;
        }

        return PageManager.LOGIN;
    }
}
