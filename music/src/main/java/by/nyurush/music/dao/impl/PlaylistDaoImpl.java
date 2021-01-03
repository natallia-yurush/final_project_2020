package by.nyurush.music.dao.impl;

import by.nyurush.music.dao.AbstractDao;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.entity.Playlist;
import by.nyurush.music.entity.Track;
import by.nyurush.music.service.builder.PlaylistBuilder;
import by.nyurush.music.service.exception.ServiceException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlaylistDaoImpl extends AbstractDao<Playlist> {

    private static final String FIND_ALL = "SELECT playlist.id, playlist.name, playlist.visible, account_id, first_name, last_name, birth_date, email, subscription, country_code, A.id, login, password, role " +
            "FROM playlist " +
            "JOIN user U ON playlist.user_id = U.account_id " +
            "JOIN account A on U.account_id = A.id";
    private static final String FIND_BY_ID = "SELECT playlist.id, playlist.name, playlist.visible, account_id, first_name, last_name, birth_date, email, subscription, country_code, A.id, login, password, role  " +
            "FROM playlist " +
            "JOIN user U ON playlist.user_id = U.account_id " +
            "JOIN account A on U.account_id = A.id " +
            "WHERE playlist.id = ?";
    private static final String FIND_BY_NAME = "SELECT playlist.id, playlist.name, playlist.visible, account_id, first_name, last_name, birth_date, email, subscription, country_code, A.id, login, password, role  " +
            "FROM playlist " +
            "JOIN user U ON playlist.user_id = U.account_id " +
            "JOIN account A on U.account_id = A.id " +
            "WHERE playlist.name LIKE ?";
    private static final String CREATE = "INSERT INTO playlist (name, visible, user_id) VALUES (?, ?, ?)";
    private static final String ADD_TRACK = "INSERT INTO playlist_track (playlist_id, track_id) VALUES (?, ?)";
    private static final String DELETE_TRACK = "DELETE FROM playlist_track WHERE track_id = ? AND playlist_id = ?";
    private static final String UPDATE = "UPDATE playlist SET name = ?, visible = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM playlist WHERE id = ?";

    public PlaylistDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Playlist> findAll() throws DaoException {
        List<Playlist> playlistsList = new ArrayList<>();
        Playlist playlist;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                playlist = new PlaylistBuilder().build(resultSet);
                playlistsList.add(playlist);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return playlistsList;
    }

    @Override
    public Optional<Playlist> findById(Integer id) throws DaoException {
        Playlist playlist = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                playlist = new PlaylistBuilder().build(resultSet);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(playlist);
    }

    @Override
    public Playlist save(Playlist playlist) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            try {
                connection.setAutoCommit(false);
                if (playlist.getId() != null) {
                    preparedStatement = connection.prepareStatement(UPDATE, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setInt(3, playlist.getId());
                } else {
                    preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setInt(3, playlist.getUser().getId());
                }
                preparedStatement.setString(1, playlist.getPlaylistName());
                preparedStatement.setBoolean(2, playlist.getVisible());
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    playlist.setId(resultSet.getInt(1));
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
        return playlist;
    }

    @Override
    public boolean delete(Playlist playlist) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            try {
                connection.setAutoCommit(false);
                preparedStatement.setInt(1, playlist.getId());
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

    public List<Playlist> findByName(String name) throws DaoException {
        List<Playlist> playlistsList = new ArrayList<>();
        Playlist playlist;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME)) {
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                playlist = new PlaylistBuilder().build(resultSet);
                playlistsList.add(playlist);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return playlistsList;
    }

    public boolean addTrack(Playlist playlist, Track track) throws DaoException {
       // PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_TRACK)) {
                preparedStatement.setInt(1, playlist.getId());
                preparedStatement.setInt(2, track.getId());
                preparedStatement.execute();
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

    public boolean deleteTrack(Playlist playlist, Track  track) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TRACK)) {
            try {
                connection.setAutoCommit(false);
                preparedStatement.setInt(1, track.getId());
                preparedStatement.setInt(2, playlist.getId());
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

}
