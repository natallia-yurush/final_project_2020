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
        String locale = req.getParameter("pagecontent");
        session.setAttribute("pagecontent", locale);

        StringUtil stringUtil = new StringUtil();
        ResourceBundle rb = getResourceBundle(session);
        if (!stringUtil.areNotNull(lang, page)) {
            session.setAttribute("parametersInfo", rb.getString(WRONG_OPERATION_KEY));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_LOGIN);
        }


        return null;//TODO DELETE
/*

        LanguageName languageName = new LanguageName();
        if (languageName.isLangExist(lang)) {
            session.setAttribute(LOCALE_ATTRIBUTE_NAME, lang);
        } else {
            throw new UnsupportedOperationException("Unknown language: " + lang);
        }

        switch (page) {
            case "login":
                return CommandResult.forward(JspPageName.LOGIN_PAGE);
            case "main":
                return CommandResult.forward(JspPageName.MAIN_PAGE);
            case "addBooks":
                return CommandResult.redirect(JspPageRedirectPath.ADMIN_BOOKS_PAGE);
            case "addLibrarians":
                return CommandResult.redirect(JspPageRedirectPath.ADMIN_LIBRARIANS_PAGE);
            case "addReaders":
                return CommandResult.redirect(JspPageRedirectPath.ADMIN_READERS_PAGE);
            case "allOrders":
                return CommandResult.redirect(JspPageRedirectPath.LIBRARIAN_ALL_ORDERS_PAGE);
            case "issueOrders":
                return CommandResult.redirect(JspPageRedirectPath.LIBRARIAN_ISSUE_ORDERS_PAGE);
            case "readersBooks":
                return CommandResult.redirect(JspPageRedirectPath.READER_BOOKS_PAGE);
            case "readerFindBook":
                return CommandResult.redirect(JspPageRedirectPath.READER_FIND_BOOKS_PAGE);
            default:
                throw new UnsupportedOperationException("Unknown page: " + page);
        }
*/

    }

    private ResourceBundle getResourceBundle(HttpSession session) {
        Object localParameter = session.getAttribute("local");
        Locale currentLang;
        if (localParameter != null) {
            String string = String.valueOf(localParameter);
            String[] langParameters = string.split("_");
            currentLang = new Locale(langParameters[0], langParameters[1]);
        } else {
            currentLang = new Locale(DEFAULT_LANG);
        }
        return ResourceBundle.getBundle("locale", currentLang);
    }
}
