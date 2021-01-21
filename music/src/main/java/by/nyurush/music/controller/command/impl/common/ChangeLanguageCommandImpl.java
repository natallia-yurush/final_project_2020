package by.nyurush.music.controller.command.impl.common;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.controller.command.impl.user.HomeCommandImpl;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.TrackService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantPathPages;
import by.nyurush.music.util.language.GenreUtil;
import by.nyurush.music.util.language.ResourceBundleUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

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


        String uri = req.getRequestURI();
        String pageName = uri.substring(uri.lastIndexOf("/") + 1);
        String path = req.getRequestURI().substring(req.getContextPath().length());


        /*TODO: ALL PAGES*/
        switch (page) {
            case "login":
                return CommandResult.forward(ConstantPathPages.PATH_PAGE_LOGIN);
            case "register":
            case "signup":
                return CommandResult.forward(ConstantPathPages.PATH_PAGE_SIGN_UP);
            case "profilePage":
            case "profile":
                return CommandResult.forward(ConstantPathPages.PATH_PAGE_PROFILE);
            case "home":
                new HomeCommandImpl().execute(req, resp);
                return CommandResult.forward(ConstantPathPages.PATH_PAGE_HOME);
            case "addArtistPage":
                return CommandResult.forward(ConstantPathPages.PATH_PAGE_ADD_ARTIST);
            case "addMusicPage":
                return CommandResult.forward(ConstantPathPages.PATH_PAGE_ADD_SONG);
            case "addAlbumPage":
                return CommandResult.forward(ConstantPathPages.PATH_PAGE_CREATE_ALBUM);
            case "genres":
                return CommandResult.forward(ConstantPathPages.PATH_PAGE_GENRES);
            case "artists":
                return CommandResult.forward(ConstantPathPages.PATH_PAGE_ARTISTS);
            case "search":
                return CommandResult.forward(ConstantPathPages.PATH_PAGE_SEARCH);
            case "editSong":
                return CommandResult.forward(ConstantPathPages.PATH_PAGE_EDIT_SONG);
            case "addToPlaylistPage":
                return CommandResult.forward(ConstantPathPages.PATH_PAGE_ADD_TO_PLAYLIST);


            default:
                throw new UnsupportedOperationException("Unknown page: " + page);
        }


    }


}
