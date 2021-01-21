package by.nyurush.music.controller.command.impl.admin;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.entity.Track;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.TrackService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantMessages;
import by.nyurush.music.util.constant.ConstantPathPages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static by.nyurush.music.util.constant.ConstantAttributes.ERROR_MESSAGE;
import static by.nyurush.music.util.constant.ConstantAttributes.SUCCESS_MESSAGE;

public class DeleteSongCommandImpl implements Command {
    private static final Logger LOGGER = LogManager.getLogger(DeleteSongCommandImpl.class);

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        TrackService trackService = new TrackService();
        Integer id = Integer.parseInt(req.getParameter(ConstantAttributes.SONG_ID));
        Optional<Track> track = trackService.findById(id);
        if (track.isPresent()) {
            trackService.delete(track.get());
            try {
                Files.delete(Path.of(ConstantAttributes.PATH_TO_SONGS + track.get().getTrackPath()));
            } catch (IOException e) {
                LOGGER.error(e);
                req.setAttribute(ERROR_MESSAGE, ConstantMessages.INVALID_DELETE_SONG);
            }
        } else {
            req.setAttribute(ERROR_MESSAGE, ConstantMessages.INVALID_DELETE_SONG);
            LOGGER.warn("Track was not found");
        }
        req.setAttribute(SUCCESS_MESSAGE, ConstantMessages.INVALID_DELETE_SONG);
        return CommandResult.forward(ConstantPathPages.PATH_PAGE_HOME);
    }
}
