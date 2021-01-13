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
    //TODO: add insert into artist_track AND + playlist_track
    private static final String FIND_ALL =
            "SELECT track.id, track.name, track.audio_path, track.number_of_likes, genre.name, album.id, album.name, album.year, album.number_of_likes, artist.id, artist.name, artist.image_path " +
            "FROM track " +
            "JOIN genre ON track.genre_name = genre.name " +
            "JOIN album ON track.album_id = album.id " +
            "JOIN artist ON album.artist_id = artist.id";
    private static final String FIND_BY_ID =
            "SELECT  track.id, track.name, track.audio_path, track.number_of_likes, genre.name, album.id, album.name, album.year, album.number_of_likes, artist.id, artist.name, artist.image_path " +
            "FROM track " +
            "JOIN genre ON track.genre_name = genre.name " +
            "JOIN album ON track.album_id = album.id " +
            "JOIN artist on album.artist_id = artist.id " +
            "WHERE track.id=?";
    private static final String FIND_BY_NAME =
            "SELECT  track.id, track.name, track.audio_path, track.number_of_likes, genre.name, album.id, album.name, album.year, album.number_of_likes, artist.id, artist.name, artist.image_path " +
            "FROM track " +
            "JOIN genre ON track.genre_name = genre.name " +
            "JOIN album ON track.album_id = album.id " +
            "JOIN artist on album.artist_id = artist.id " +
            "WHERE track.name LIKE ?";
    private static final String FIND_BY_ALBUM_NAME =
            "SELECT  track.id, track.name, track.audio_path, track.number_of_likes, genre.name, album.id, album.name, album.year, album.number_of_likes, artist.id, artist.name, artist.image_path " +
            "FROM track " +
            "JOIN genre ON track.genre_name = genre.name " +
            "JOIN album ON track.album_id = album.id " +
            "JOIN artist on album.artist_id = artist.id " +
            "WHERE album.name LIKE ?";
    private static final String FIND_BY_GENRE =
            "SELECT  track.id, track.name, track.audio_path, track.number_of_likes, genre.name, album.id, album.name, album.year, album.number_of_likes, artist.id, artist.name, artist.image_path " +
            "FROM track " +
            "JOIN genre ON track.genre_name = genre.name " +
            "JOIN album ON track.album_id = album.id " +
            "JOIN artist on album.artist_id = artist.id " +
            "WHERE track.genre_name=?";
    private static final String FIND_BY_ARTIST =
            "SELECT track.id, track.name, track.audio_path, track.number_of_likes, genre.name, album.id, album.name, album.year, album.number_of_likes, artist.id, artist.name, artist.image_path " +
            "FROM artist_track " +
            "JOIN track ON track.id = artist_track.track_id " +
            "JOIN genre ON track.genre_name = genre.name " +
            "JOIN album ON track.album_id = album.id " +
            "JOIN artist ON album.artist_id = artist.id " +
            "WHERE artist.name LIKE ?";
    private static final String FIND_BY_PLAYLIST_ID =
            "SELECT track.id, track.name, track.audio_path, track.number_of_likes, genre.name, album.id, album.name, album.year, album.number_of_likes, artist.id, artist.name, artist.image_path, playlist.id, playlist.name, playlist.visible " +
            "FROM playlist_track " +
            "JOIN playlist on playlist_track.playlist_id = playlist.id " +
            "JOIN track ON playlist_track.track_id = track.id " +
            "JOIN genre ON track.genre_name = genre.name " +
            "JOIN album ON track.album_id = album.id " +
            "JOIN artist ON album.artist_id = artist.id " +
            "WHERE playlist.id = ?";
    private static final String FIND_BY_PLAYLIST_NAME = "SELECT track.id, track.name, track.audio_path, track.number_of_likes, genre.name, album.id, album.name, album.year, album.number_of_likes, artist.id, artist.name, artist.image_path, playlist.id, playlist.name, playlist.visible " +
            "FROM playlist_track " +
            "JOIN playlist ON playlist_track.playlist_id = playlist.id " +
            "JOIN track ON playlist_track.track_id = track.id " +
            "JOIN genre ON track.genre_name = genre.name " +
            "JOIN album ON track.album_id = album.id " +
            "JOIN artist ON album.artist_id = artist.id " +
            "WHERE playlist.name LIKE ? AND playlist.visible = 1";
    private static final String CREATE = "INSERT INTO track (name, audio_path, number_of_likes, genre_name, album_id) VALUES (?, ?, ?, ?, ?)";
    private static final String ADD_ARTIST_TRACK = "INSERT INTO artist_track (artist_id, track_id) VALUES (?, ?)";

    private static final String UPDATE = "UPDATE track SET name = ?, audio_path = ?, number_of_likes = ?, genre_name = ?, album_id = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM track WHERE id=?";
    private static final String FIND_ALL_GENRES = "SELECT name FROM genre";

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
    public Track save(Track track) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            try {
                connection.setAutoCommit(false);
                if (track.getId() != null) {
                    preparedStatement = connection.prepareStatement(UPDATE, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setInt(6, track.getId());
                } else {
                    preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
                }
                preparedStatement.setString(1, track.getTrackName());
                preparedStatement.setString(2, track.getTrackPath());
                preparedStatement.setInt(3, track.getNumberOfLikes());
                preparedStatement.setString(4, track.getGenre());
                preparedStatement.setInt(5, track.getAlbum().getId());
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    track.setId(resultSet.getInt(1));
                    preparedStatement = connection.prepareStatement(ADD_ARTIST_TRACK);
                    preparedStatement.setInt(1, track.getAlbum().getArtist().getId());
                    preparedStatement.setInt(2, track.getId());
                    preparedStatement.execute();
                }
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
        return track;
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

    public List<String> findAllGenres() throws DaoException {
        List<String> genres = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_GENRES);
            while (resultSet.next()) {
                genres.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return genres;
    }
}
