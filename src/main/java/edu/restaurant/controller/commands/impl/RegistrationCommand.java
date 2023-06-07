package edu.restaurant.controller.commands.impl;

import edu.restaurant.datasource.entities.User;
import edu.restaurant.datasource.entities.UserRole;
import edu.restaurant.manager.PageManager;
import edu.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationCommand {

    private final UserService accountService;

    @Autowired
    public RegistrationCommand(UserService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/registration")
    public String post(@RequestParam String email, @RequestParam String password, @RequestParam String confirmPassword) throws Exception {
        if(email.isEmpty() || password.isEmpty() || !password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Invalid password or email");
        }
        User user = new User(email, password.trim(), UserRole.CUSTOMER);
        accountService.registerAccount(user);
        return PageManager.LOGIN_REDIRECT;
    }

    @GetMapping("/registration")
    public String get() {
        return PageManager.REGISTRATION;
    }

}
