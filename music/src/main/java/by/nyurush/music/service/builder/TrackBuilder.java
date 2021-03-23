package by.nyurush.music.service.builder;

import by.nyurush.music.entity.Album;
import by.nyurush.music.entity.Track;
import by.nyurush.music.service.exception.ServiceException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackBuilder implements Builder<Track> {
    @Override
    public Track build(ResultSet resultSet) throws ServiceException {
        try {
            Integer id = resultSet.getInt("track.id");
            String name = resultSet.getString("track.name");
            String audioPath = resultSet.getString("track.audio_path");
            String genre = resultSet.getString("genre.name");
            Album album = new AlbumBuilder().build(resultSet);
            return new Track(id, name, audioPath, genre, album);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
