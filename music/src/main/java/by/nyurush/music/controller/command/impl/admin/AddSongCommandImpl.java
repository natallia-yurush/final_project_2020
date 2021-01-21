package by.nyurush.music.controller.command.impl.admin;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.entity.Album;
import by.nyurush.music.entity.Track;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.AlbumService;
import by.nyurush.music.service.impl.ArtistService;
import by.nyurush.music.service.impl.TrackService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantMessages;
import by.nyurush.music.util.constant.ConstantPathPages;
import by.nyurush.music.util.language.ResourceBundleUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static by.nyurush.music.util.constant.ConstantAttributes.*;

public class AddSongCommandImpl implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AddSongCommandImpl.class);

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        String songName = null;
        String genre = null;
        String songFile = null;
        List<String> artistsName = new ArrayList<>();
        String albumName = null;
        Integer id = Integer.parseInt(req.getParameter(ConstantAttributes.SONG_ID));
        ResourceBundle rb = ResourceBundleUtil.getResourceBundle(req);

        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    String fieldName = item.getFieldName();
                    switch (fieldName) {
                        case SONG_NAME:
                            songName = item.getString(UTF_8);
                            break;
                        case GENRE:
                            genre = item.getString(UTF_8);
                            break;
                        case ARTISTS_NAME:
                            artistsName.add(item.getString(UTF_8));
                            break;
                        case ALBUM:
                            albumName = item.getString(UTF_8);
                            break;
                        default:
                            LOGGER.warn("There is no such item");
                    }
                } else {
                    songFile = new File(item.getName()).getName();
                    String filePath = ConstantAttributes.PATH_TO_SONGS + songFile;
                    File storeFile = new File(filePath);
                    item.write(storeFile);
                }
            }
        } catch (Exception e) {
            req.setAttribute(ERROR_MESSAGE, rb.getString(ConstantMessages.INVALID_SONG_SAVE_RESULT));
        }

        AlbumService albumService = new AlbumService();
        Optional<Album> album = albumService.findByArtistAndAlbumName(artistsName.get(0), albumName);
        if (album.isPresent()) {
            Track track = new Track(id, songName, songFile, 0, genre, album.get());
            TrackService trackService = new TrackService();
            if (trackService.save(track) != null) {
                req.setAttribute(SUCCESS_MESSAGE, rb.getString(ConstantMessages.SUCCESSFUL_SONG_SAVE_RESULT));
            } else {
                req.setAttribute(ERROR_MESSAGE, rb.getString(ConstantMessages.INVALID_SONG_SAVE_RESULT));
                LOGGER.warn(rb.getString(ConstantMessages.INVALID_SONG_SAVE_RESULT));
            }
        } else {
            req.setAttribute(ERROR_MESSAGE, rb.getString(ConstantMessages.INVALID_SONG_SAVE_RESULT));
        }

        ArtistService artistService = new ArtistService();
        req.setAttribute(ConstantAttributes.ARTISTS_NAME, artistService.findAll());
        //TODO: not all albums
        req.setAttribute(ConstantAttributes.ALBUMS, albumService.findAll());

        return CommandResult.forward(ConstantPathPages.PATH_PAGE_ADD_SONG);
    }
}
