package by.nyurush.music.controller.command.impl.common;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.entity.Playlist;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.PlaylistService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantMessages;
import by.nyurush.music.util.constant.ConstantPathPages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static by.nyurush.music.util.constant.ConstantAttributes.ERROR_MESSAGE;
import static by.nyurush.music.util.constant.ConstantAttributes.SUCCESS_MESSAGE;

public class DeletePlaylistCommandImpl implements Command {
    private static final Logger LOGGER = LogManager.getLogger(DeletePlaylistCommandImpl.class);

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        PlaylistService playlistService = new PlaylistService();
        Integer id = Integer.parseInt(req.getParameter(ConstantAttributes.PLAYLIST_ID));
        Optional<Playlist> playlist = playlistService.findById(id);
        if (playlist.isPresent()) {
            playlistService.delete(playlist.get());
        } else {
            LOGGER.warn("Playlist was not found");
            req.setAttribute(ERROR_MESSAGE, ConstantMessages.INVALID_FIND_SONG);
        }
        req.setAttribute(SUCCESS_MESSAGE, ConstantMessages.SUCCESSFUL_DELETE_PLAYLIST);
        return CommandResult.forward(ConstantPathPages.PATH_PAGE_PLAYLISTS);
    }
}
