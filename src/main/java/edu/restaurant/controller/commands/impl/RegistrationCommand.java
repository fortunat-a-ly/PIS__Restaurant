package edu.restaurant.controller.commands.impl;

import edu.restaurant.controller.commands.Command;
import edu.restaurant.datasource.entities.User;
import edu.restaurant.datasource.entities.UserRole;
import edu.restaurant.manager.PageManager;
import edu.restaurant.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {

    private final UserService accountService = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equals("GET")) {
            return PageManager.REGISTRATION;
        } else {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirm_password");

            if(email.isEmpty() || password.isEmpty() || !password.equals(confirmPassword)) {
                throw new IllegalArgumentException("Invalid password or email");
            }

            User user = new User(email, password, UserRole.CUSTOMER);

            try {
                accountService.registerAccount(user);
                return PageManager.LOGIN;
            } catch (Exception e) {
                throw e;
            }
        }
    }

}
