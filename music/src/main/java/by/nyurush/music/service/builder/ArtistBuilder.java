package by.nyurush.music.service.builder;

import by.nyurush.music.entity.Artist;
import by.nyurush.music.service.exception.ServiceException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArtistBuilder implements Builder<Artist> {
    @Override
    public Artist build(ResultSet resultSet) throws ServiceException {
        try {
            return new Artist(resultSet.getInt("artist.id"), resultSet.getString("artist.name"));
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
