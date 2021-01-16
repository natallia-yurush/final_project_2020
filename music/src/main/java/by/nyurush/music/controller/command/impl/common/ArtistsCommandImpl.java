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

public class ArtistsCommandImpl implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        try {

            String albumName = req.getParameter(ConstantAttributes.ALBUM_NAME);
            ArtistService artistService = new ArtistService(new DaoHelperFactory());
            req.setAttribute(ConstantAttributes.ARTISTS_LIST, artistService.findAll());


            TrackService trackService = new TrackService(new DaoHelperFactory());
            if(albumName == null || albumName.isEmpty()) {
                req.setAttribute(ConstantAttributes.SONGS_LIST, trackService.findByArtist(req.getParameter(ConstantAttributes.ARTIST_NAME)));
            } else {
                req.setAttribute(ConstantAttributes.SONGS_LIST, trackService.findByAlbumName(albumName));
            }

            AlbumService albumService = new AlbumService(new DaoHelperFactory());
            req.setAttribute(ConstantAttributes.ALBUMS, albumService.findByArtistName(req.getParameter(ConstantAttributes.ARTIST_NAME)));

        } catch (ServiceException e) {
            e.printStackTrace();//todo
        }


        return CommandResult.forward(ConstantPathPages.PATH_PAGE_ARTISTS);
    }
}
