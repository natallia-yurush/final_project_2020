package by.nyurush.music.controller.command.impl.user;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.entity.User;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.UserService;
import by.nyurush.music.util.constant.ConstantMessages;
import by.nyurush.music.util.constant.ConstantPathPages;
import by.nyurush.music.util.language.ResourceBundleUtil;
import by.nyurush.music.util.validation.DataValidator;
import by.nyurush.music.util.validation.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

import static by.nyurush.music.util.constant.ConstantAttributes.*;

public class ProfileCommandImpl implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        HttpSession session = req.getSession();
        ResourceBundle rb = ResourceBundleUtil.getResourceBundle(req);
        User currentUser = (User) session.getAttribute(USER);

        String firstName = req.getParameter(FIRST_NAME);
        String lastName = req.getParameter(LAST_NAME);
        String email = req.getParameter(EMAIL);
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);

        if (!StringUtil.areNotNullAndNotEmpty(firstName) || !DataValidator.isCorrectNameSurname(firstName)) {
            req.setAttribute(INVALID_FIRST_NAME, rb.getString(ConstantMessages.INVALID_NAME));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_PROFILE);
        }
        if (!StringUtil.areNotNullAndNotEmpty(lastName) || !DataValidator.isCorrectNameSurname(lastName)) {
            req.setAttribute(INVALID_LAST_NAME, rb.getString(ConstantMessages.INVALID_NAME));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_PROFILE);
        }
        if (!StringUtil.areNotNullAndNotEmpty(login) || !DataValidator.isCorrectLogin(login)) {
            req.setAttribute(INVALID_LOGIN, rb.getString(ConstantMessages.INVALID_LOGIN));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_PROFILE);
        }
        if (!StringUtil.areNotNullAndNotEmpty(email) || !DataValidator.isCorrectEmail(email)) {
            req.setAttribute(INVALID_EMAIL, rb.getString(ConstantMessages.INVALID_EMAIL));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_PROFILE);
        }
        if (!StringUtil.areNotNullAndNotEmpty(password) || !DataValidator.isCorrectPassword(password)) {
            req.setAttribute(INVALID_PASS, rb.getString(ConstantMessages.INVALID_PASSWORD));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_PROFILE);
        }

        currentUser.setFirstName(firstName);
        currentUser.setLastName(lastName);
        currentUser.setEmail(email);
        currentUser.setLogin(login);
        if (!StringUtil.isNullOrEmpty(password)) {
            currentUser.setPassword(password);
        }

        UserService userService = new UserService();
        userService.save(currentUser);
        session.setAttribute(USER, currentUser);

        req.setAttribute(SUCCESS_MESSAGE, rb.getString(ConstantMessages.SUCCESSFUL_CHANGES));
        return CommandResult.forward(ConstantPathPages.PATH_PAGE_PROFILE);
    }
}
