package by.nyurush.music.controller.command.impl.common;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.AlbumService;
import by.nyurush.music.service.impl.ArtistService;
import by.nyurush.music.service.impl.TrackService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantPathPages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchCommandImpl implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        try {
            String input = req.getParameter(ConstantAttributes.SEARCH_INPUT);

            ArtistService artistService = new ArtistService(new DaoHelperFactory());
            req.setAttribute(ConstantAttributes.ARTISTS_LIST, artistService.findByName(input));


            TrackService trackService = new TrackService(new DaoHelperFactory());
            req.setAttribute(ConstantAttributes.SONGS_LIST, trackService.findByName(input));


            AlbumService albumService = new AlbumService(new DaoHelperFactory());
            req.setAttribute(ConstantAttributes.ALBUMS, albumService.findByName(input));

        } catch (ServiceException e) {
            e.printStackTrace();//todo
        }


        return CommandResult.forward(ConstantPathPages.PATH_PAGE_SEARCH);

        //return null; //TODO ?
    }
}
