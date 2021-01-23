package by.nyurush.music.controller.command.impl.common;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.controller.command.impl.user.HomeCommandImpl;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.TrackService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.language.GenreUtil;
import by.nyurush.music.util.language.ResourceBundleUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

public class ChangeLanguageCommandImpl implements Command {


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

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
        if (langCookie == null) {
            langCookie = new Cookie(ConstantAttributes.LANGUAGE, ConstantAttributes.DEFAULT_LANG);
        }

        if (lang.contains(ConstantAttributes.EN_LANG) || lang.contains(ConstantAttributes.RU_LANG)) {
            langCookie.setValue(lang);
            resp.addCookie(langCookie);
        } else {
            throw new UnsupportedOperationException("Unknown language: " + lang);
        }

        ResourceBundle rb = ResourceBundleUtil.getResourceBundle(req);
        TrackService trackService = new TrackService();
        session.setAttribute(ConstantAttributes.GENRES, GenreUtil.getGenres(trackService.findAllGenres(), rb));
        new HomeCommandImpl().execute(req, resp);

        //TODO: redirect

        String url = req.getRequestURL().toString();
        String path = req.getPathInfo();
        String a = req.getPathTranslated();

        StringBuffer s = new StringBuffer(req.getServletPath());
       // Enumeration parameterNames = ;
        List<String> headerValuesList= Collections.list(req.getParameterNames());
        s.append("?");
        /*for(Object headerValueObj: headerValuesList) {

        }*/


        Iterator<String> itr = headerValuesList.iterator();
        while(true) {
            String param = itr.next();
            if(itr.hasNext()) {
                s.append(param).append("=").append(req.getParameter(param)).append("&");
            } else {
                s.append(param).append("=").append(req.getParameter(param));
                break;
            }
        }
       /* Object lastElement = itr.next();
        while(itr.hasNext()) {
            lastElement = itr.next();
        }*/




        
        String f = req.getRequestURI();

        return CommandResult.redirect(req.getServletPath());


        //return CommandResult.redirect(req.getRequestURI());
       // return CommandResult.forward(page.split(req.getContextPath(), 2)[1]);
    }
}
