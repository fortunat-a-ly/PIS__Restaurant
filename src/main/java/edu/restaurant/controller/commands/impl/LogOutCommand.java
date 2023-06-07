package edu.restaurant.controller.commands.impl;

import edu.restaurant.manager.PageManager;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogOutCommand {
    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public String execute(HttpSession session) {
        session.invalidate();
        return PageManager.LOGIN_REDIRECT;
    }

}
