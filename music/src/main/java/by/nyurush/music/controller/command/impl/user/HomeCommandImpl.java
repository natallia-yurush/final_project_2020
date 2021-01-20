package by.nyurush.music.controller.command.impl.user;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.dao.DaoHelperFactory;
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
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        try {
            int page = 1;
            int recordsPerPage = 10;
            if (req.getParameter("pageNo") != null)
                page = Integer.parseInt(req.getParameter("pageNo"));
            TrackService trackService = new TrackService();
            List<Track> list = trackService.findForPage((page - 1) * recordsPerPage, recordsPerPage);
            int noOfRecords = trackService.getNoOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

            req.setAttribute("songs", list);
            req.setAttribute("noOfPages", noOfPages);
            req.setAttribute("currentPage", page);


/*
            List<Track> list = dao.viewAllEmployees((page - 1) * recordsPerPage, recordsPerPage);
            int noOfRecords = dao.getNoOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            request.setAttribute("employeeList", list);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            RequestDispatcher view = request.getRequestDispatcher("employee.jsp");
            view.forward(request, response);
*/


        } catch (ServiceException e) {
            e.printStackTrace();//todo
        }


/*
        try {
            TrackService trackService = new TrackService(new DaoHelperFactory());
            req.setAttribute(ConstantAttributes.SONGS, trackService.findAll());


        } catch (ServiceException e) {
            e.printStackTrace();//todo
        }
*/


        return CommandResult.forward(ConstantPathPages.PATH_PAGE_HOME);
    }
}
