package by.nyurush.music.controller.command.impl.admin;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.entity.Album;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.AlbumService;
import by.nyurush.music.service.impl.ArtistService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantPathPages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddMusicPageCommandImpl implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        //TODO: заполнить артистов, жанры
        try {
            ArtistService artistService = new ArtistService(new DaoHelperFactory());
            req.setAttribute(ConstantAttributes.ARTISTS_NAME, artistService.findAll());

            //TODO: список альбомов должен изменяться!
            AlbumService albumService = new AlbumService(new DaoHelperFactory());
            req.setAttribute(ConstantAttributes.ALBUMS, albumService.findAll());

        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return CommandResult.forward(ConstantPathPages.PATH_PAGE_ADD_MUSIC);
    }
}
