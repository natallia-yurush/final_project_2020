package by.nyurush.music.controller.command.impl.common;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.TrackService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantPathPages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GenresCommandImpl implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        try {
            TrackService trackService = new TrackService(new DaoHelperFactory());
            req.setAttribute(ConstantAttributes.SONGS_LIST, trackService.findByGenre(req.getParameter(ConstantAttributes.GENRE)));


        } catch (ServiceException e) {
            e.printStackTrace();//todo
        }


        return CommandResult.forward(ConstantPathPages.PATH_PAGE_GENRES);
    }
}
