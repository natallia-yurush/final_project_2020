package by.nyurush.music.controller.command.impl.admin;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.util.constant.ConstantAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

public class CreateAlbumCommandImpl implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String albumName = req.getParameter(ConstantAttributes.ALBUM_NAME);
        Integer year = Integer.valueOf(req.getParameter(ConstantAttributes.YEAR));
        List<String> artistName = Arrays.asList(req.getParameterValues(ConstantAttributes.ARTISTS_NAME));


        return null;


    }
}
