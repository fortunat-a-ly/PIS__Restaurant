package edu.restaurant.controller.commands.impl;

import edu.restaurant.manager.PageManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorCommand {
    @RequestMapping(value = "/error", method = {RequestMethod.GET, RequestMethod.POST})
    public String execute() {
        return PageManager.ERROR;
    }
}
