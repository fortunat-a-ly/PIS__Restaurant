package edu.restaurant.manager;

import edu.restaurant.controller.commands.Command;
import edu.restaurant.controller.commands.impl.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class CommandManager {
    private static final String URL_REG_EXP = ".*/restaurant";
    private static final Map<String, Command> commands = Map.of(
        "/registration", new RegistrationCommand(),
        "/login", new LogInCommand(),
        "/logout", new LogOutCommand(),
        "/meals", new MealsCommand(),
        "/orders", new OrderCommand(),
            "/error", new ErrorCommand()
    );

    public static Command getCommand(HttpServletRequest request) {
        String path = request.getRequestURI();
        path = path.replaceAll(URL_REG_EXP, "");
        System.out.println(path);
        return commands.getOrDefault(path, new LogInCommand());
    }
}
