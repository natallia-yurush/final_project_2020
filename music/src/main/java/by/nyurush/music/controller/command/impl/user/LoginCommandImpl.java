package by.nyurush.music.controller.command.impl.user;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.entity.Account;
import by.nyurush.music.entity.AccountRole;
import by.nyurush.music.entity.User;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.AccountService;
import by.nyurush.music.service.impl.TrackService;
import by.nyurush.music.service.impl.UserService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantMessages;
import by.nyurush.music.util.constant.ConstantPathPages;
import by.nyurush.music.util.language.GenreUtil;
import by.nyurush.music.util.language.ResourceBundleUtil;
import by.nyurush.music.util.validation.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginCommandImpl implements Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        HttpSession session = req.getSession();
        req.setAttribute(ConstantAttributes.ERROR_AUTH, false);

        String login = req.getParameter(ConstantAttributes.LOGIN);
        String password = req.getParameter(ConstantAttributes.PASSWORD);

        ResourceBundle rb = ResourceBundleUtil.getResourceBundle(req);

        if (!StringUtil.areNotNull(login, password)) {
            req.setAttribute(ConstantAttributes.PARAM_INFO, rb.getString(ConstantMessages.WRONG_OPERATION_KEY));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_LOGIN);
        }
        TrackService trackService = new TrackService();
        session.setAttribute(ConstantAttributes.GENRES, GenreUtil.getGenres(trackService.findAllGenres(), rb));


        AccountService accountService = new AccountService();
        UserService userService = new UserService();

        Optional<Account> account = accountService.isAccountExist(login, password);
        if (account.isPresent()) {
            Account acc = account.get();
            new HomeCommandImpl().execute(req, resp);
            if (acc.getRole() == AccountRole.ADMIN) {
                session.setAttribute(ConstantAttributes.USER, acc);
            } else {
                Optional<User> user = userService.findByLogin(login);
                user.ifPresent(value -> session.setAttribute(ConstantAttributes.USER, value));
            }
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_HOME);
        }
        req.setAttribute(ConstantAttributes.ERROR_AUTH, rb.getString(ConstantMessages.AUTHORISATION_FAILED_KEY));
        return CommandResult.forward(ConstantPathPages.PATH_PAGE_LOGIN);
    }


}
