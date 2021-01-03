package by.nyurush.music.controller.command.impl.user;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.controller.command.ResourceBundleCommand;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantMessages;
import by.nyurush.music.util.constant.ConstantPathPages;
import by.nyurush.music.util.validation.DataValidator;
import by.nyurush.music.util.validation.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

public class RegisterCommandImpl implements Command {
    private static Logger LOGGER = LogManager.getLogger(RegisterCommandImpl.class);

/*
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        return null;
    }
*/

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            //TODO
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        ResourceBundle rb = ResourceBundleCommand.getResourceBundle(session);

        String page;
        String name = request.getParameter(ConstantAttributes.FIRST_NAME);
        if (!StringUtil.areNotNullAndNotEmpty(name) || !DataValidator.isCorrectNameSurname(name)) {
            LOGGER.info("invalid first name format was received:" + name);
            request.setAttribute(ConstantAttributes.INVALID_FIRST_NAME, rb.getString(ConstantMessages.INVALID_NAME));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_SIGN_UP);
        }
        String lastName = request.getParameter(ConstantAttributes.LAST_NAME);
        if (!StringUtil.areNotNullAndNotEmpty(lastName) || !DataValidator.isCorrectNameSurname(lastName)) {
            LOGGER.info("invalid name format was received:" + lastName);
            request.setAttribute(ConstantAttributes.INVALID_LAST_NAME, rb.getString(ConstantMessages.INVALID_NAME));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_SIGN_UP);
        }
        String login = request.getParameter(ConstantAttributes.LOGIN);
        if (!StringUtil.areNotNullAndNotEmpty(login) || !DataValidator.isCorrectLogin(login)) {
            LOGGER.info("invalid login format was received:" + login);
            request.setAttribute(ConstantAttributes.INVALID_LOGIN, rb.getString(ConstantMessages.INVALID_LOGIN));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_SIGN_UP);
        }
        String email = request.getParameter(ConstantAttributes.EMAIL);
        if (!StringUtil.areNotNullAndNotEmpty(email) || !DataValidator.isCorrectEmail(email)){
            LOGGER.info("invalid email format was received:" + email);
            request.setAttribute(ConstantAttributes.INVALID_EMAIL, rb.getString(ConstantMessages.INVALID_EMAIL));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_SIGN_UP);
        }
        String password = request.getParameter(ConstantAttributes.PASSWORD);
        if (!StringUtil.areNotNullAndNotEmpty(password) || !DataValidator.isCorrectPassword(password)){
            LOGGER.info("invalid password format was received");
            request.setAttribute(ConstantAttributes.INVALID_PASS, rb.getString(ConstantMessages.INVALID_PASSWORD));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_SIGN_UP);
        }
        String confPassword = request.getParameter(ConstantAttributes.CONFIRM_PASSWORD);
        if (!StringUtil.areNotNullAndNotEmpty(confPassword) || !DataValidator.isCorrectPassword(confPassword)){
            LOGGER.info("invalid confirm password format was received");
            request.setAttribute(ConstantAttributes.INVALID_CONFIRM_PASS, rb.getString(ConstantMessages.INVALID_PASSWORD));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_SIGN_UP);
        }

        //TODO
        return CommandResult.forward(ConstantPathPages.PATH_PAGE_LOGIN);

/*


        Client client;
        try {
            client = buildUser(request, userHash);
            if (clientService.registerUser(client)) {
                SendingEmail.verify(login, email, userHash);
                log.info("client with login = " + login + " was registered. Activation Link was sent.");
                page = Page.VERIFY_PAGE;
            } else {
                request.setAttribute(WRONG_DATA, true);
                return new CommandResult(Page.REGISTER_PAGE);
            }
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.REGISTER_PAGE;
        }
        return new CommandResult(page, true);
*/

    }
/*

    private Client buildUser(HttpServletRequest request, String userHash) throws ServiceException {
        String name = request.getParameter(PARAM_NAME);
        String surname = request.getParameter(PARAM_SURNAME);
        String login = request.getParameter(PARAM_LOGIN);
        String email = request.getParameter(PARAM_EMAIL);
        String password = request.getParameter(PARAM_PASSWORD);
        String newPassword = DigestUtils.sha512Hex(password);
        float personalDiscount = SALE_SYSTEM.getSaleByVisitNumber(START_VISIT_NUMBER);
        Program program = buildProgram();
        return new Client(null, null, name, surname, login, newPassword, email, userHash, false, START_VISIT_NUMBER,
                personalDiscount, program.getId(), null, null);
    }

*/

}
