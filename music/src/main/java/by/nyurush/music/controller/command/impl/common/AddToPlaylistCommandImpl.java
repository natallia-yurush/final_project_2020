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
import by.nyurush.music.util.constant.ConstantPathPages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AddToPlaylistCommandImpl implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String playlistName = req.getParameter(ConstantAttributes.PLAYLIST_NAME);
        Integer songId = Integer.parseInt(req.getParameter(ConstantAttributes.SONG_ID));


        try {
            Account account = (Account) req.getSession().getAttribute(ConstantAttributes.USER);
            PlaylistService playlistService = new PlaylistService();
            Optional<Playlist> playlist = playlistService.findByNameAndUserId(playlistName, account.getId());
            TrackService trackService = new TrackService();
            Optional<Track> track = trackService.findById(songId);

            if(playlist.isPresent() && track.isPresent()) {
                playlistService.addTrack(playlist.get(), track.get());
            }

        } catch (ServiceException e) {
            e.printStackTrace();
        }

        new HomeCommandImpl().execute(req, resp);
        return CommandResult.forward(ConstantPathPages.PATH_PAGE_HOME);
    }
}
