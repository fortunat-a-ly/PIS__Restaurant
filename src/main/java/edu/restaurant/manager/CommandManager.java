package edu.restaurant.manager;

import edu.restaurant.controller.commands.Command;
import edu.restaurant.controller.commands.impl.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class CommandManager {
    private static final String URL_REG_EXP = ".*/restaurant/";
    private static final Map<String, Command> commands = Map.of(
        "registration.jsp", new RegistrationCommand(),
        "login.jsp", new LogInCommand(),
        "logout.jsp", new LogOutCommand(),
        "meals.jsp", new MealsCommand(),
        "orders.jsp", new OrderCommand()
    );

    public static Command getCommand(HttpServletRequest request) {
        String path = request.getRequestURI();
        path = path.replaceAll(URL_REG_EXP, "");
        return commands.getOrDefault(path, new LogInCommand());
    }
}
