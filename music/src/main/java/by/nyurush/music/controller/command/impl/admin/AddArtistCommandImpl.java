package by.nyurush.music.controller.command.impl.admin;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.entity.Artist;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.ArtistService;
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
import java.util.List;
import java.util.ResourceBundle;

import static by.nyurush.music.util.constant.ConstantAttributes.UTF_8;

public class AddArtistCommandImpl implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AddArtistCommandImpl.class);

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        String artistName = null;
        String artistImg = null;
        ResourceBundle rb = ResourceBundleUtil.getResourceBundle(req);

        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                    artistName = item.getString(UTF_8);
                } else {
                    // Process form file field (input type="file").
                    String fileName = new File(item.getName()).getName();
                    artistImg = fileName;
                    String filePath = ConstantAttributes.PATH_TO_ARTISTS_IMAGES + fileName;
                    File storeFile = new File(filePath);
                    item.write(storeFile);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
            req.setAttribute(ConstantAttributes.ERROR_MESSAGE, rb.getString(ConstantMessages.INVALID_ARTIST_SAVE_RESULT));
        }
        Artist artist = new Artist(null, artistName, artistImg);
        ArtistService artistService = new ArtistService();
        if (artistService.save(artist) != null) {
            req.setAttribute(ConstantAttributes.SUCCESS_MESSAGE, rb.getString(ConstantMessages.SUCCESSFUL_ARTIST_SAVE_RESULT));
        } else {
            req.setAttribute(ConstantAttributes.ERROR_MESSAGE, rb.getString(ConstantMessages.INVALID_ARTIST_SAVE_RESULT));
            LOGGER.warn(ConstantMessages.INVALID_ARTIST_SAVE_RESULT);
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_ADD_ARTIST);
        }
        return CommandResult.forward(ConstantPathPages.PATH_PAGE_ADD_ARTIST);
    }
}