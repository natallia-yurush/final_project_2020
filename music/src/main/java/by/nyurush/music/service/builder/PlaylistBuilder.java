package by.nyurush.music.service.builder;

import by.nyurush.music.entity.Playlist;
import by.nyurush.music.entity.User;
import by.nyurush.music.service.exception.ServiceException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistBuilder implements Builder<Playlist> {
    @Override
    public Playlist build(ResultSet resultSet) throws ServiceException {
        try {
            User user = new UserBuilder().build(resultSet);
            Integer id = resultSet.getInt("playlist.id");
            String name = resultSet.getString("playlist.name");
            Boolean visible = resultSet.getBoolean("playlist.visible");
            return new Playlist(id, name, visible, user);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
