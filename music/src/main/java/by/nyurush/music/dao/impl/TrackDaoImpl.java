package by.nyurush.music.dao.impl;

import by.nyurush.music.dao.AbstractDao;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.entity.Track;
import by.nyurush.music.service.builder.TrackBuilder;
import by.nyurush.music.service.exception.ServiceException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TrackDaoImpl extends AbstractDao<Track> {
    private static final String FIND_ALL = "SELECT T.*, G.name, A.* FROM track T JOIN genre G ON T.genre_name = G.name JOIN album A ON T.album_id = A.id";
    private static final String FIND_BY_ID = "SELECT T.*, G.name, A.* FROM track T JOIN genre G ON T.genre_name = G.name JOIN album A ON T.album_id = A.id WHERE T.id=?";
    private static final String FIND_BY_NAME = "SELECT T.*, G.name, A.* FROM track T JOIN genre G ON T.genre_name = G.name JOIN album A ON T.album_id = A.id WHERE T.name LIKE ?";
    private static final String FIND_BY_ALBUM_NAME = "SELECT T.*, G.name, A.* FROM track T JOIN genre G ON T.genre_name = G.name JOIN album A ON T.album_id = A.id WHERE A.name LIKE ?";
    private static final String FIND_BY_GENRE = "SELECT T.*, G.name, A.* FROM track T JOIN genre G ON T.genre_name = G.name JOIN album A ON T.album_id = A.id WHERE T.genre_name=?";
    private static final String FIND_BY_ARTIST = "SELECT T.*, Al.artist_id, Ar.name FROM artist_track AT  JOIN track T ON T.id = AT.track_id " +
            "JOIN album Al ON T.album_id = Al.id JOIN artist Ar ON Al.artist_id = Ar.id WHERE Ar.name LIKE ?";
    private static final String FIND_BY_PLAYLIST_ID = "SELECT T.*, P.* FROM playlist_track PT JOIN playlist P on PT.playlist_id = p.id JOIN track T on PT.track_id = t.id WHERE P.id = ?";
    private static final String FIND_BY_PLAYLIST_NAME = "SELECT T.*, P.* FROM playlist_track PT JOIN playlist P on PT.playlist_id = p.id JOIN track T on PT.track_id = t.id WHERE P.name LIKE ? AND P.visible = 1";
    private static final String CREATE = "INSERT INTO track (name, audio_path, number_of_likes, genre_name, album_id) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE track SET name = ?, audio_path = ?, number_of_likes = ?, genre_name = ?, album_id = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM track WHERE id=?";

    public TrackDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Track> findAll() throws DaoException {
        List<Track> tracksList = new ArrayList<>();
        Track track;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                track = new TrackBuilder().build(resultSet);
                tracksList.add(track);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return tracksList;
    }

    @Override
    public Optional<Track> findById(Integer id) throws DaoException {
        Track track = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                track = new TrackBuilder().build(resultSet);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(track);
    }

    @Override
    public boolean save(Track track) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            try {
                connection.setAutoCommit(false);
                if (track.getId() != null) {
                    preparedStatement = connection.prepareStatement(UPDATE);
                    preparedStatement.setInt(6, track.getId());
                } else {
                    preparedStatement = connection.prepareStatement(CREATE);
                }
                preparedStatement.setString(1, track.getTrackName());
                preparedStatement.setString(2, track.getTrackPath());
                preparedStatement.setInt(3, track.getNumberOfLikes());
                preparedStatement.setString(4, track.getGenre());
                preparedStatement.setInt(5, track.getAlbum().getId());
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DaoException();
        }
        return true;
    }

    @Override
    public boolean delete(Track track) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            try {
                connection.setAutoCommit(false);
                preparedStatement.setLong(1, track.getId());
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return true;
    }

    public List<Track> findByName(String name) throws DaoException {
        List<Track> tracksList = new ArrayList<>();
        Track track;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME)) {
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                track = new TrackBuilder().build(resultSet);
                tracksList.add(track);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return tracksList;
    }

    public List<Track> findByAlbumName(String name) throws DaoException {
        List<Track> tracksList = new ArrayList<>();
        Track track;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ALBUM_NAME)) {
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                track = new TrackBuilder().build(resultSet);
                tracksList.add(track);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return tracksList;
    }

    public List<Track> findByGenre(String genre) throws DaoException {
        List<Track> tracksList = new ArrayList<>();
        Track track;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_GENRE)) {
            preparedStatement.setString(1, genre);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                track = new TrackBuilder().build(resultSet);
                tracksList.add(track);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return tracksList;
    }

    public List<Track> findByArtist(String name) throws DaoException {
        List<Track> tracksList = new ArrayList<>();
        Track track;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ARTIST)) {
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                track = new TrackBuilder().build(resultSet);
                tracksList.add(track);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return tracksList;
    }

    public List<Track> findByPlaylistId(Integer playlistId) throws DaoException {
        List<Track> tracksList = new ArrayList<>();
        Track track;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_PLAYLIST_ID)) {
            preparedStatement.setInt(1, playlistId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                track = new TrackBuilder().build(resultSet);
                tracksList.add(track);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return tracksList;
    }

    public List<Track> findByPlaylistName(String playlistName) throws DaoException {
        List<Track> tracksList = new ArrayList<>();
        Track track;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_PLAYLIST_NAME)) {
            preparedStatement.setString(1, "%" + playlistName + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                track = new TrackBuilder().build(resultSet);
                tracksList.add(track);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return tracksList;
    }
}
