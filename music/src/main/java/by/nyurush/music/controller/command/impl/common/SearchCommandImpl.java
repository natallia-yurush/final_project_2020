package by.nyurush.music.controller.command.impl.common;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.entity.Track;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.AlbumService;
import by.nyurush.music.service.impl.ArtistService;
import by.nyurush.music.service.impl.TrackService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantPathPages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class SearchCommandImpl implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String input = req.getParameter(ConstantAttributes.SEARCH_INPUT);
        byte[] bytes = input.getBytes(StandardCharsets.ISO_8859_1);
        input = new String(bytes, StandardCharsets.UTF_8);

        ArtistService artistService = new ArtistService();
        req.setAttribute(ConstantAttributes.ARTISTS_LIST, artistService.findByName(input));

        int page = 1;
        int recordsPerPage = 10;
        if (req.getParameter(ConstantAttributes.PAGE_NO) != null)
            page = Integer.parseInt(req.getParameter(ConstantAttributes.PAGE_NO));
        TrackService trackService = new TrackService();
        List<Track> list = trackService.findByName(input, (page - 1) * recordsPerPage, recordsPerPage);
        int noOfRecords = trackService.getNoOfRecordsByName(input);
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        req.setAttribute(ConstantAttributes.SONGS, list);
        req.setAttribute(ConstantAttributes.NO_OF_PAGES, noOfPages);
        req.setAttribute(ConstantAttributes.CURRENT_PAGE, page);

        AlbumService albumService = new AlbumService();
        req.setAttribute(ConstantAttributes.ALBUMS, albumService.findByName(input));

        return CommandResult.forward(ConstantPathPages.PATH_PAGE_SEARCH);
    }
}
