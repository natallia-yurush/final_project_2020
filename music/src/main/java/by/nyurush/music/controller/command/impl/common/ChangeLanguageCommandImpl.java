package by.nyurush.music.controller.command.impl.common;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantMessages;
import by.nyurush.music.util.constant.ConstantPathPages;
import by.nyurush.music.util.language.ResourceBundleUtil;
import by.nyurush.music.util.validation.StringUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

public class ChangeLanguageCommandImpl implements Command {


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        String lang = req.getParameter("lang");
        String page = req.getParameter("page");

        HttpSession session = req.getSession();

        Cookie langCookie = null;

        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals(ConstantAttributes.LANGUAGE)) {
                langCookie = cookie;
                break;
            }
        }
        if(langCookie == null) {
            langCookie = new Cookie(ConstantAttributes.LANGUAGE, ConstantAttributes.DEFAULT_LANG);
        }

       // ResourceBundle rb = ResourceBundleUtil.getResourceBundle(req);
        /*
        if (!StringUtil.areNotNull(page)) {
            session.setAttribute("parametersInfo", rb.getString(ConstantMessages.WRONG_OPERATION_KEY));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_LOGIN);
        }
*/


        if (lang.contains(ConstantAttributes.EN_LANG) || lang.contains(ConstantAttributes.RU_LANG)) {
            langCookie.setValue(lang);
            resp.addCookie(langCookie);
        } else {
            throw new UnsupportedOperationException("Unknown language: " + lang);
        }

        //TODO: All pages
        System.out.println(req.getContextPath());
        System.out.println(req.getRequestURI());
        System.out.println(req.getRequestURL());
        System.out.println(req.getHeader("Refer"));


        String uri = req.getRequestURI();

        String pageName = uri.substring(uri.lastIndexOf("/")+1);


        String path = req.getRequestURI().substring(req.getContextPath().length());


        /*return CommandResult.forward(req.getContextPath());*/

        switch (page) {
            case "login":
                return CommandResult.forward(ConstantPathPages.PATH_PAGE_LOGIN);
            case "register":
            case "signup":
                return CommandResult.forward(ConstantPathPages.PATH_PAGE_SIGN_UP);
            case "profile":
                return CommandResult.forward(ConstantPathPages.PATH_PAGE_PROFILE);
            case "home":
                return CommandResult.forward(ConstantPathPages.PATH_PAGE_HOME); //redirect??




            default:
                throw new UnsupportedOperationException("Unknown page: " + page);
        }


    }


}
