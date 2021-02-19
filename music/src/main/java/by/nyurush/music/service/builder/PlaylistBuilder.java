package by.nyurush.music.service.builder;

import by.nyurush.music.entity.Account;
import by.nyurush.music.entity.Playlist;
import by.nyurush.music.service.exception.ServiceException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistBuilder implements Builder<Playlist> {
    @Override
    public Playlist build(ResultSet resultSet) throws ServiceException {
        try {
            Account account = new AccountBuilder().build(resultSet);
            Integer id = resultSet.getInt("playlist.id");
            String name = resultSet.getString("playlist.name");
            Boolean visible = resultSet.getBoolean("playlist.visible");
            return new Playlist(id, name, visible, account);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
