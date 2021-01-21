package by.nyurush.music.controller.command.impl.user;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.entity.Track;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.TrackService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantPathPages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class HomeCommandImpl implements Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        int page = 1;
        int recordsPerPage = 10;
        if (req.getParameter(ConstantAttributes.PAGE_NO) != null)
            page = Integer.parseInt(req.getParameter(ConstantAttributes.PAGE_NO));
        TrackService trackService = new TrackService();
        List<Track> list = trackService.findForPage((page - 1) * recordsPerPage, recordsPerPage);
        int noOfRecords = trackService.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        req.setAttribute(ConstantAttributes.SONGS, list);
        req.setAttribute(ConstantAttributes.NO_OF_PAGES, noOfPages);
        req.setAttribute(ConstantAttributes.CURRENT_PAGE, page);

        return CommandResult.forward(ConstantPathPages.PATH_PAGE_HOME);
    }
}
