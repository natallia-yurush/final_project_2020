package by.nyurush.music.controller.command.impl.admin;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.entity.Album;
import by.nyurush.music.entity.Track;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.AlbumService;
import by.nyurush.music.service.impl.TrackService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantMessages;
import by.nyurush.music.util.constant.ConstantPathPages;
import by.nyurush.music.util.language.ResourceBundleUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static by.nyurush.music.util.constant.ConstantAttributes.*;

public class AddMusicCommandImpl implements Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        String songName = null;// = req.getParameter(ARTIST_NAME);
        String genre = null;// = req.getParameter(ARTIST_IMAGE);
        String songFile = null;
        List<String> artistsName = new ArrayList<>();
        String albumName = null;




        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    String fieldName = item.getFieldName();
                    // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                    switch (fieldName) {
                        case SONG_NAME:
/*

                            ObjectInputStream ois = new ObjectInputStream(item.getInputStream());
                            object = ois.readObject();
*/

                            songName = item.getString(UTF_8);
                            break;
                        case GENRE:
                            genre = item.getString(UTF_8);
                            break;
                        case ARTISTS_NAME:
                            artistsName.add(item.getString(UTF_8));
                           // artistsName = Arrays.asList(item.getString());
                            break;
                        case ALBUM:
                            albumName = item.getString(UTF_8);
                            break;
                        default:
                            //TODO logger
                    }
                    //artistName = item.getString();
                   /* String fieldValue = item.getString();
                    artistName = fieldValue;*/
                    // ... (do your job here)
                } else {
                    // Process form file field (input type="file").
                   // String fileName = new File(item.getName()).getName();
                    //TODO: вынести в константу
                   // songFile = fileName;
                    songFile = new File(item.getName()).getName();
                    String filePath = "D:\\JAVA_ST_2020\\final_project\\music\\web\\resource\\songs\\" + songFile;

                    File storeFile = new File(filePath);
                    item.write(storeFile);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            //TODO
        }


        Album album = null;
        try {
            AlbumService albumService = new AlbumService(new DaoHelperFactory());
            album = albumService.findByArtistAndAlbumName(artistsName.get(0), albumName).get();
        } catch (ServiceException e) {
            e.printStackTrace();
        }




        Track track = new Track(null, songName, songFile, 0, genre, album);
        ResourceBundle rb = ResourceBundleUtil.getResourceBundle(req);
        try {
            TrackService trackService = new TrackService(new DaoHelperFactory());
            trackService.save(track);

            req.setAttribute(ConstantAttributes.SAVE_RESULT, rb.getString(ConstantMessages.SUCCESSFUL_SONG_SAVE_RESULT));


        } catch (ServiceException e) {
            req.setAttribute(ConstantAttributes.SAVE_RESULT, rb.getString(ConstantMessages.INVALID_SONG_SAVE_RESULT));
            e.printStackTrace();//TODO
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_ADD_ARTIST);
        }


        return CommandResult.forward(ConstantPathPages.PATH_PAGE_ADD_MUSIC);


    }
}
