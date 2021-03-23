package by.nyurush.music.service.builder;

import by.nyurush.music.entity.*;
import by.nyurush.music.service.exception.ServiceException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AlbumBuilder implements Builder<Album> {
    @Override
    public Album build(ResultSet resultSet) throws ServiceException {
        try {
            Artist artist = new ArtistBuilder().build(resultSet);
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Integer year = resultSet.getInt("year");
            return new Album(id, name, year, artist);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
