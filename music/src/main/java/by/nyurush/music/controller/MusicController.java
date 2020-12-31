package by.nyurush.music.controller;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandFactory;
import by.nyurush.music.controller.command.CommandResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MusicController", urlPatterns = "/controller")
public class MusicController extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(MusicController.class);
    private static final String PARAMETER_COMMAND = "command";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter = req.getParameter(PARAMETER_COMMAND);

        Command command = CommandFactory.create(parameter);

        CommandResult page = command.execute(req, resp);
        dispatch(req, resp, page);

    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp, CommandResult page)
            throws ServletException, IOException {
        String pageToDispatch = page.getPage();

        if (page.isRedirect()) {
            resp.sendRedirect(req.getContextPath() + pageToDispatch);
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pageToDispatch);
            dispatcher.forward(req, resp);
        }
    }
}