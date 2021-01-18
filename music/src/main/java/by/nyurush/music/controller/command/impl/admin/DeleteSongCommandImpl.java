package by.nyurush.music.controller.command.impl.admin;

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
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DeleteSongCommandImpl implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        /*TODO: add message about successful or not delete*/
        try {
            TrackService trackService = new TrackService();
            Integer id = Integer.parseInt(req.getParameter(ConstantAttributes.SONG_ID));
            Track track = trackService.findById(id).get();
            trackService.delete(track);


            //удаление файла с использованием относительного пути к файлу

            Files.delete(Path.of("D:\\JAVA_ST_2020\\final_project\\music\\web\\resource\\songs\\" + track.getTrackPath()));


        } catch (ServiceException e) {
            e.printStackTrace();//todo
        } catch (IOException e) {
            //TODO!!!!!!
            e.printStackTrace();
        }


        return CommandResult.forward(ConstantPathPages.PATH_PAGE_HOME);
    }
}
