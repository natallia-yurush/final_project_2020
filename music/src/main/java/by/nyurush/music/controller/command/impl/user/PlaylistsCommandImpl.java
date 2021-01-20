package by.nyurush.music.controller.command.impl.user;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.entity.Account;
import by.nyurush.music.entity.Playlist;
import by.nyurush.music.entity.Track;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.AlbumService;
import by.nyurush.music.service.impl.ArtistService;
import by.nyurush.music.service.impl.PlaylistService;
import by.nyurush.music.service.impl.TrackService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantPathPages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class PlaylistsCommandImpl implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        try {

            String playlistName = req.getParameter(ConstantAttributes.PLAYLIST_NAME);
            Account account = (Account) req.getSession().getAttribute(ConstantAttributes.USER);
            PlaylistService playlistService = new PlaylistService();
            Optional<Playlist> playlist = playlistService.findByNameAndUserId(playlistName, account.getId());

            if(playlist.isPresent()) {
                int page = 1;
                int recordsPerPage = 10;
                if (req.getParameter("pageNo") != null)
                    page = Integer.parseInt(req.getParameter("pageNo"));
                TrackService trackService = new TrackService();
                List<Track> list = trackService.findByPlaylistId(playlist.get().getId(), (page - 1) * recordsPerPage, recordsPerPage);
                int noOfRecords = trackService.getNoOfRecordsByPlaylistId(playlist.get().getId());
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

                req.setAttribute("songs", list);
                req.setAttribute("noOfPages", noOfPages);
                req.setAttribute("currentPage", page);
            }

            req.setAttribute(ConstantAttributes.PLAYLIST_LIST, playlistService.findByUserId(account.getId()));

        } catch (ServiceException e) {
            e.printStackTrace();//todo
        }


        return CommandResult.forward(ConstantPathPages.PATH_PAGE_PLAYLISTS);
    }
}
