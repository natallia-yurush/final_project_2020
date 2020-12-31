package by.nyurush.music.controller.command.impl.common;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.util.StringUtil;
import by.nyurush.music.util.constant.ConstantPathPages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChangeLanguageCommandImpl implements Command {
    private static final String DEFAULT_LANG = "";
    private static final String WRONG_OPERATION_KEY = "answer.wrong.operation";
    private static final String LOCALE_ATTRIBUTE_NAME = "local";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        String lang = req.getParameter("lang");
        String page = req.getParameter("page");

        HttpSession session = req.getSession();

        StringUtil stringUtil = new StringUtil();
        ResourceBundle rb = getResourceBundle(session);
        if (!stringUtil.areNotNull(lang, page)) {
            session.setAttribute("parametersInfo", rb.getString(WRONG_OPERATION_KEY));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_LOGIN);
        }

        if(lang.contains("en_En") || lang.contains("ru_RU")) {
            session.setAttribute(LOCALE_ATTRIBUTE_NAME, lang);
        } else {
            throw new UnsupportedOperationException("Unknown language: " + lang);
        }

        switch (page) {
            case "login":
                return CommandResult.forward(ConstantPathPages.PATH_PAGE_LOGIN);
            case "signup":
                return CommandResult.forward(ConstantPathPages.PATH_PAGE_SIGN_UP);

            default:
                throw new UnsupportedOperationException("Unknown page: " + page);
        }


    }

    //TODO: вынести куда-нибудь эту функцию (много раз повторяется)
    private ResourceBundle getResourceBundle(HttpSession session) {
        Object localParameter = session.getAttribute("javax.servlet.jsp.jstl.fmt.locale.session");
        Locale currentLang;
        if (localParameter != null) {
            String string = String.valueOf(localParameter);
            String[] langParameters = string.split("_");
            currentLang = new Locale(langParameters[0], langParameters[1]);
        } else {
            currentLang = new Locale(DEFAULT_LANG);
        }
        return ResourceBundle.getBundle("pagecontent", currentLang);
    }
}
