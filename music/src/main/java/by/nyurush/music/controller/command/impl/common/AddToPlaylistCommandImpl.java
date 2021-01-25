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
import by.nyurush.music.util.validation.DataValidator;
import by.nyurush.music.util.validation.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AddToPlaylistCommandImpl implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AddToPlaylistCommandImpl.class);

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String playlistName = req.getParameter(ConstantAttributes.PLAYLIST_NAME);
        if(StringUtil.isNullOrEmpty(playlistName) || DataValidator.isCorrectInput(playlistName)) {
            req.setAttribute(ConstantAttributes.INFO_MESSAGE, ConstantMessages.FILL_WITH_CORRECT_DATA);
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_ADD_TO_PLAYLIST);
        }
        Integer songId = Integer.parseInt(req.getParameter(ConstantAttributes.SONG_ID));
        Account account = (Account) req.getSession().getAttribute(ConstantAttributes.USER);
        PlaylistService playlistService = new PlaylistService();
        Optional<Playlist> playlist = playlistService.findByNameAndUserId(playlistName, account.getId());
        TrackService trackService = new TrackService();
        Optional<Track> track = trackService.findById(songId);

        if (playlist.isPresent() && track.isPresent()) {
            playlistService.addTrack(playlist.get(), track.get());
        } else {
            LOGGER.warn("Playlist or Track was not found");
            req.setAttribute(ConstantAttributes.ERROR_MESSAGE, ConstantMessages.INVALID_ADD_PLAYLIST_RESULT);
        }
        req.setAttribute(ConstantAttributes.SUCCESS_MESSAGE, ConstantMessages.SUCCESSFUL_ADD_TO_PLAYLIST_RESULT);
        new HomeCommandImpl().execute(req, resp);
        return CommandResult.forward(ConstantPathPages.PATH_PAGE_HOME);
    }
}
