package by.nyurush.music.controller.command.impl.user;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.entity.User;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.UserService;
import by.nyurush.music.util.constant.ConstantPathPages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.nyurush.music.util.constant.ConstantAttributes.*;

public class ProfileCommandImpl implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("user");

        //String firstName =
        currentUser.setFirstName(req.getParameter(FIRST_NAME));
        currentUser.setLastName(req.getParameter(LAST_NAME));
        currentUser.setEmail(req.getParameter(EMAIL));
        currentUser.setLogin(req.getParameter(LOGIN));
        currentUser.setPassword(req.getParameter(PASSWORD));

        //TODO: data checking

        //TODO: ввод старого пароля
        try {
            UserService userService = new UserService(new DaoHelperFactory());
            userService.save(currentUser);

        } catch (ServiceException e) {
            //TODO
            e.printStackTrace();
        }

        return CommandResult.forward(ConstantPathPages.PATH_PAGE_PROFILE);

    }
}
