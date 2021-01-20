package by.nyurush.music.controller.command.impl.common;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.entity.Playlist;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.PlaylistService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantPathPages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class DeletePlaylistCommandImpl implements Command {

    /*TODO: add message*/
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            PlaylistService playlistService = new PlaylistService();
            Integer id = Integer.parseInt(req.getParameter(ConstantAttributes.PLAYLIST_ID));
            Optional<Playlist> playlist = playlistService.findById(id);
            if(playlist.isPresent()) {
                playlistService.delete(playlist.get());
            }

        } catch (ServiceException e) {
            e.printStackTrace();//todo
        }

        return CommandResult.forward(ConstantPathPages.PATH_PAGE_PLAYLISTS);
    }
}
