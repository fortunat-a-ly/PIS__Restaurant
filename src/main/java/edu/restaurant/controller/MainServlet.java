package edu.restaurant.controller;

import edu.restaurant.controller.commands.Command;
import edu.restaurant.controller.commands.impl.LogInCommand;
import edu.restaurant.manager.CommandManager;
import edu.restaurant.manager.PageManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class MainServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println("SERVLET");
            String webPage = null;
            Command command = CommandManager.getCommand(request);
            webPage = command.execute(request, response);


            if (webPage.startsWith("redirect")) {
                response.sendRedirect(webPage.substring(8));
                return;
            }

            request.getRequestDispatcher(webPage).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(PageManager.ERROR_REDIRECT.substring(8));
        }
    }
}
