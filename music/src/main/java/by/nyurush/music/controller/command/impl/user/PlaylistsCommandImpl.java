package by.nyurush.music.controller.command.impl.user;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
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
import java.util.List;
import java.util.Optional;

import static by.nyurush.music.util.constant.ConstantAttributes.ERROR_MESSAGE;

public class PlaylistsCommandImpl implements Command {
    private static final Logger LOGGER = LogManager.getLogger(PlaylistsCommandImpl.class);

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String playlistName = req.getParameter(ConstantAttributes.PLAYLIST_NAME);
        Account account = (Account) req.getSession().getAttribute(ConstantAttributes.USER);
        PlaylistService playlistService = new PlaylistService();
        Optional<Playlist> playlist = playlistService.findByNameAndUserId(playlistName, account.getId());
        if (playlist.isPresent()) {
            int page = 1;
            int recordsPerPage = 10;
            if (req.getParameter(ConstantAttributes.PAGE_NO) != null)
                page = Integer.parseInt(req.getParameter(ConstantAttributes.PAGE_NO));
            TrackService trackService = new TrackService();
            List<Track> list = trackService.findByPlaylistId(playlist.get().getId(), (page - 1) * recordsPerPage, recordsPerPage);
            int noOfRecords = trackService.getNoOfRecordsByPlaylistId(playlist.get().getId());
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

            req.setAttribute(ConstantAttributes.SONGS, list);
            req.setAttribute(ConstantAttributes.NO_OF_PAGES, noOfPages);
            req.setAttribute(ConstantAttributes.CURRENT_PAGE, page);
        } else {
            req.setAttribute(ERROR_MESSAGE, ConstantMessages.INVALID_FIND_PLAYLIST);
            LOGGER.warn("Playlist was not found");
        }
        req.setAttribute(ConstantAttributes.PLAYLIST_LIST, playlistService.findByUserId(account.getId()));
        return CommandResult.forward(ConstantPathPages.PATH_PAGE_PLAYLISTS);
    }
}
