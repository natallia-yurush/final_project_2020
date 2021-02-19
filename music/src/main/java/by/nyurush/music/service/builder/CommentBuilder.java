package by.nyurush.music.service.builder;

import by.nyurush.music.entity.Comment;
import by.nyurush.music.entity.Track;
import by.nyurush.music.entity.User;
import by.nyurush.music.service.exception.ServiceException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class CommentBuilder implements Builder<Comment> {
    @Override
    public Comment build(ResultSet resultSet) throws ServiceException {
        try {
            User user = new UserBuilder().build(resultSet);
            Track track = new Track(resultSet.getInt("comment.track_id"));
            Integer id = resultSet.getInt("comment.id");
            String text = resultSet.getString("comment.text");
            /*Calendar cal = Calendar.getInstance();
            cal.setTimeZone(TimeZone.getTimeZone("UTC"));*/
            Date date = resultSet.getTimestamp("comment.date");
            String path = resultSet.getString("comment.path");
            return new Comment(id, text, date, path, track, user);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
