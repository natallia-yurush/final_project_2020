package by.nyurush.music.controller.command.impl.common;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.controller.command.impl.user.HomeCommandImpl;
import by.nyurush.music.entity.Account;
import by.nyurush.music.entity.Playlist;
import by.nyurush.music.entity.Track;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.PlaylistService;
import by.nyurush.music.service.impl.TrackService;
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

public class CreatePlaylistAndAddSongCommandImpl implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CreatePlaylistAndAddSongCommandImpl.class);

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String playlistName = req.getParameter(ConstantAttributes.PLAYLIST_NAME);
        Integer songId = Integer.parseInt(req.getParameter(ConstantAttributes.SONG_ID));
        Account account = (Account) req.getSession().getAttribute(ConstantAttributes.USER);
        PlaylistService playlistService = new PlaylistService();
        Playlist playlist = playlistService.save(new Playlist(null, playlistName, false, account));
        TrackService trackService = new TrackService();
        Optional<Track> track = trackService.findById(songId);
        if (track.isPresent()) {
            playlistService.addTrack(playlist, track.get());
        } else {
            LOGGER.warn("Track was not found");
            req.setAttribute(ERROR_MESSAGE, ConstantMessages.INVALID_FIND_SONG);
        }
        req.setAttribute(SUCCESS_MESSAGE, ConstantMessages.SUCCESSFUL_ADD_TO_PLAYLIST_RESULT);
        new HomeCommandImpl().execute(req, resp);
        return CommandResult.forward(ConstantPathPages.PATH_PAGE_HOME);
    }
}
