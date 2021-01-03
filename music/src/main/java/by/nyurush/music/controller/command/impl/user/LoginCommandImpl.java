package by.nyurush.music.controller.command.impl.user;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.controller.command.ResourceBundleCommand;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.entity.Account;
import by.nyurush.music.entity.AccountRole;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.AccountService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantMessages;
import by.nyurush.music.util.validation.StringUtil;
import by.nyurush.music.util.constant.ConstantPathPages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginCommandImpl implements Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession();

        //TODO: necessary or not?
        //session.removeAttribute("user");
        //session.removeAttribute("role");

        //session.setAttribute(ConstantAttributes.ERROR_AUTH, null);
        req.setAttribute(ConstantAttributes.ERROR_AUTH, false);

        String login = req.getParameter(ConstantAttributes.LOGIN);
        String password = req.getParameter(ConstantAttributes.PASSWORD);

        ResourceBundle rb = ResourceBundleCommand.getResourceBundle(session);
        StringUtil stringUtil = new StringUtil();
        if (!stringUtil.areNotNull(login, password)) {
            req.setAttribute(ConstantAttributes.PARAM_INFO, rb.getString(ConstantMessages.WRONG_OPERATION_KEY));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_LOGIN);
        }

        try {
            AccountService accountService = new AccountService(new DaoHelperFactory());

            Optional<Account> account = accountService.isAccountExist(login, password);
            if (account.isPresent()) {
                /*session.setAttribute("role", user.get().getRole());*/
                /*session.setAttribute("user", account.get());*/
                if(account.get().getRole() == AccountRole.ADMIN) {
                    return CommandResult.forward(ConstantPathPages.PATH_PAGE_SIGN_UP);
                    //TODO ADMIN
                    //return CommandResult.forward(ConstantPathPages.PATH_PAGE_MAIN); //ADMIN
                } else {
                    //TODO CLIENT + можно найти именно юзера и установить его в сессию, а может и не нужно:)
                    //return CommandResult.forward(ConstantPathPages.PATH_PAGE_MAIN); //CLIENT
                }


            }

            req.setAttribute(ConstantAttributes.ERROR_AUTH, rb.getString(ConstantMessages.AUTHORISATION_FAILED_KEY));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_LOGIN);
        } catch (ServiceException e) {
            e.printStackTrace();//TODO LOGGER ( AND throw? )
        }
        return null; //todo delete this line after logger?
    }


}
