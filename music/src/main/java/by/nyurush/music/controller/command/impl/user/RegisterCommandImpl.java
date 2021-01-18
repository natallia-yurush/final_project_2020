package by.nyurush.music.controller.command.impl.user;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.entity.AccountRole;
import by.nyurush.music.entity.User;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.UserService;
import by.nyurush.music.util.constant.ConstantMessages;
import by.nyurush.music.util.constant.ConstantPathPages;
import by.nyurush.music.util.language.ResourceBundleUtil;
import by.nyurush.music.util.validation.DataValidator;
import by.nyurush.music.util.validation.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

import static by.nyurush.music.util.constant.ConstantAttributes.*;

public class RegisterCommandImpl implements Command {
    private static Logger LOGGER = LogManager.getLogger(RegisterCommandImpl.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        ResourceBundle rb = ResourceBundleUtil.getResourceBundle(request);

        String name = request.getParameter(FIRST_NAME);
        if (!StringUtil.areNotNullAndNotEmpty(name) || !DataValidator.isCorrectNameSurname(name)) {
            LOGGER.info("invalid first name format was received:" + name);
            request.setAttribute(INVALID_FIRST_NAME, rb.getString(ConstantMessages.INVALID_NAME));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_SIGN_UP);
        }
        String lastName = request.getParameter(LAST_NAME);
        if (!StringUtil.areNotNullAndNotEmpty(lastName) || !DataValidator.isCorrectNameSurname(lastName)) {
            LOGGER.info("invalid name format was received:" + lastName);
            request.setAttribute(INVALID_LAST_NAME, rb.getString(ConstantMessages.INVALID_NAME));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_SIGN_UP);
        }
        String login = request.getParameter(LOGIN);
        if (!StringUtil.areNotNullAndNotEmpty(login) || !DataValidator.isCorrectLogin(login)) {
            LOGGER.info("invalid login format was received:" + login);
            request.setAttribute(INVALID_LOGIN, rb.getString(ConstantMessages.INVALID_LOGIN));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_SIGN_UP);
        }
        String email = request.getParameter(EMAIL);
        if (!StringUtil.areNotNullAndNotEmpty(email) || !DataValidator.isCorrectEmail(email)){
            LOGGER.info("invalid email format was received:" + email);
            request.setAttribute(INVALID_EMAIL, rb.getString(ConstantMessages.INVALID_EMAIL));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_SIGN_UP);
        }
        String password = request.getParameter(PASSWORD);
        if (!StringUtil.areNotNullAndNotEmpty(password) || !DataValidator.isCorrectPassword(password)){
            LOGGER.info("invalid password format was received");
            request.setAttribute(INVALID_PASS, rb.getString(ConstantMessages.INVALID_PASSWORD));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_SIGN_UP);
        }
        String confPassword = request.getParameter(CONFIRM_PASSWORD);
        if (!StringUtil.areNotNullAndNotEmpty(confPassword) || !DataValidator.isCorrectPassword(confPassword)){
            LOGGER.info("invalid confirm password format was received");
            request.setAttribute(INVALID_CONFIRM_PASS, rb.getString(ConstantMessages.INVALID_PASSWORD));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_SIGN_UP);
        }
        if(!password.equals(confPassword)) {
            LOGGER.info("passwords do not match");
            request.setAttribute(INVALID_PASS_MATCH, rb.getString(ConstantMessages.INVALID_PASSWORDS_MATCH));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_SIGN_UP);
        }



        User user;
        try {
            user = buildUser(request);
            UserService userService = new UserService();
            if (userService.isFreeLogin(user.getLogin())) {

                //SendingEmail.verify(login, email);  //TODO: send email

                session.setAttribute(USER, userService.save(user));
                LOGGER.info("user with login = " + login + " was registered.");

            } else {
                request.setAttribute(INVALID_LOGIN, ConstantMessages.TAKEN_LOGIN);
                return CommandResult.forward(ConstantPathPages.PATH_PAGE_SIGN_UP);
            }
        } catch (ServiceException e) {
            LOGGER.error("Problem with service occurred!", e);
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_SIGN_UP);
        }
        return CommandResult.redirect(ConstantPathPages.PATH_PAGE_HOME);

    }

    private User buildUser(HttpServletRequest request) {
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        String login = request.getParameter(LOGIN);
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        return new User(null, login, password, AccountRole.CLIENT, firstName, lastName, email, false);
    }


}
