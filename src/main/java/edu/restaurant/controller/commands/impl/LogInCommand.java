package edu.restaurant.controller.commands.impl;

import edu.restaurant.datasource.entities.User;
import edu.restaurant.manager.PageManager;
import edu.restaurant.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LogInCommand {

    private final UserService userService;

    @Autowired
    public LogInCommand(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public String defaultMapping() {
        System.err.println("LOGIN");
        return PageManager.LOGIN;
    }

    @GetMapping("/login")
    public String get(HttpSession session) {
        System.err.println("LOGIN");
        if(session != null && session.getAttribute("id") != null) {
            return PageManager.MEALS_REDIRECT;
        }
        System.err.println("LOGIN");
        return PageManager.LOGIN;
    }

    @PostMapping("/login")
    public String post(@RequestParam String email, @RequestParam String password, HttpSession session) throws Exception {
        User user = userService.getAccountByEmail(email);
        if(!user.getPassword().trim().equals(password.trim())) {
            throw new IllegalArgumentException("Wrong password");
        }
        session.setAttribute("id", user.getId());
        session.setAttribute("role", user.getRole());
        return PageManager.MEALS_REDIRECT;
    }

}
