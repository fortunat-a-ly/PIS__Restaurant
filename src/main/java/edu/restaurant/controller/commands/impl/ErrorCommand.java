package edu.restaurant.controller.commands.impl;

import edu.restaurant.controller.commands.Command;
import edu.restaurant.manager.PageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return PageManager.ERROR;
    }
}
