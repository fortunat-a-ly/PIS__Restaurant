package edu.restaurant.controller.commands.impl;

import edu.restaurant.controller.commands.Command;
import edu.restaurant.datasource.entities.User;
import edu.restaurant.datasource.entities.UserRole;
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
        if (request.getMethod().equals("GET")) {
            HttpSession session = request.getSession(false);
            System.out.println("GET1");
            if(session != null && session.getAttribute("id") != null) {
                return PageManager.MEALS_REDIRECT;
            }
            System.out.println("GET2");
            return PageManager.LOGIN;
        } else if (request.getMethod().equals("POST")) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            try {
                User user = userService.getAccountByEmail(email);
                request.getSession().setAttribute("id", user.getId());
                request.getSession().setAttribute("role", user.getRole());

                return PageManager.MEALS_REDIRECT;
            } catch (Exception e) {
                throw e;
            }
        }
        return PageManager.LOGIN;
    }
}
