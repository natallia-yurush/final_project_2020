package by.nyurush.music.controller.command.impl.admin;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.util.constant.ConstantPathPages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddArtistPageCommandImpl implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        return CommandResult.forward(ConstantPathPages.PATH_PAGE_ADD_ARTIST);
    }
}
