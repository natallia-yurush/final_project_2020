package by.nyurush.music.controller.command.impl.common;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.entity.Account;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.PlaylistService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantPathPages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddToPlaylistPageCommandImpl implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        try {

            PlaylistService playlistService = new PlaylistService();

            Account account = (Account) req.getSession().getAttribute(ConstantAttributes.USER);
            req.setAttribute(ConstantAttributes.PLAYLIST_LIST, playlistService.findByUserId(account.getId()));

        } catch (ServiceException e) {
            e.printStackTrace();//todo
        }

        return CommandResult.forward(ConstantPathPages.PATH_PAGE_ADD_TO_PLAYLIST);
    }
}
