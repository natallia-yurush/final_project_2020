package by.nyurush.music.controller.command.impl.user;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.entity.Account;
import by.nyurush.music.entity.AccountRole;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.AccountService;
import by.nyurush.music.util.StringUtil;
import by.nyurush.music.util.constant.ConstantPathPages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginCommandImpl implements Command {
    private static final String WRONG_OPERATION_KEY = "answer.wrongOperation";
    private static final String AUTHORISATION_FAILED_KEY = "label.failedAuthorization";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession();
        session.removeAttribute("user");
        session.removeAttribute("role");
        session.setAttribute("errorAuthorisation", null);

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        ResourceBundle rb = getResourceBundle(session);
        StringUtil stringUtil = new StringUtil();
        if (!stringUtil.areNotNull(login, password)) {
            req.setAttribute("parametersInfo", rb.getString(WRONG_OPERATION_KEY));
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

            req.setAttribute("errorAuthorisation", rb.getString(AUTHORISATION_FAILED_KEY));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_LOGIN);
        } catch (ServiceException e) {
            e.printStackTrace();//TODO LOGGER ( AND throw? )
        }
        return null; //todo delete this line after logger?
    }

    private ResourceBundle getResourceBundle(HttpSession session) {
        Object localParameter = session.getAttribute("javax.servlet.jsp.jstl.fmt.locale.session");
        Locale currentLang;
        if (localParameter != null) {
            String string = String.valueOf(localParameter);
            String[] langParameters = string.split("_");
            currentLang = new Locale(langParameters[0], langParameters[1]);
        } else {
            currentLang = new Locale("");
        }
        return ResourceBundle.getBundle("pagecontent", currentLang);
    }
}
