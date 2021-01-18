package by.nyurush.music.controller.command.impl.admin;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.entity.Album;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.AlbumService;
import by.nyurush.music.service.impl.ArtistService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantMessages;
import by.nyurush.music.util.constant.ConstantPathPages;
import by.nyurush.music.util.language.ResourceBundleUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ResourceBundle;

public class AddAlbumCommandImpl implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String albumName = req.getParameter(ConstantAttributes.ALBUM_NAME);
        Integer year = Integer.valueOf(req.getParameter(ConstantAttributes.YEAR));
        String artistsName = req.getParameter(ConstantAttributes.ARTISTS_NAME);

        ResourceBundle rb = ResourceBundleUtil.getResourceBundle(req);
        try {
            AlbumService albumService = new AlbumService();
            ArtistService artistService = new ArtistService();

            albumService.save(new Album(null, albumName, year, 0, artistService.findByName(artistsName).get(0)));

            req.setAttribute(ConstantAttributes.SAVE_RESULT, rb.getString(ConstantMessages.SUCCESSFUL_ALBUM_SAVE_RESULT));
        } catch (ServiceException e) {
            req.setAttribute(ConstantAttributes.SAVE_RESULT, rb.getString(ConstantMessages.INVALID_ALBUM_SAVE_RESULT));
            e.printStackTrace();//TODO
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_CREATE_ALBUM);
        }


        return CommandResult.forward(ConstantPathPages.PATH_PAGE_CREATE_ALBUM);

    }
}
