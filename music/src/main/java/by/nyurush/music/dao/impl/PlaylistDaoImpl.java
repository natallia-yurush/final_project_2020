package by.nyurush.music.dao.impl;

import by.nyurush.music.dao.AbstractDao;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.entity.Playlist;
import by.nyurush.music.service.builder.PlaylistBuilder;
import by.nyurush.music.service.exception.ServiceException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlaylistDaoImpl extends AbstractDao<Playlist> {
    private static final String FIND_ALL = "SELECT P.*, U.* FROM playlist P JOIN user U ON P.user_id = U.account_id ";
    private static final String FIND_BY_ID = "SELECT P.*, U.* FROM playlist P JOIN user U ON P.user_id = U.account_id WHERE P.id = ?";
    private static final String FIND_BY_NAME = "SELECT P.*, U.* FROM playlist P JOIN user U ON P.user_id = U.account_id WHERE P.name = ?";
    private static final String CREATE = "INSERT INTO playlist (name, visible, user_id) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE playlist SET name = ?, visible = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM playlist WHERE id = ?";

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
    public boolean save(Playlist playlist) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            try {
                connection.setAutoCommit(false);
                if (playlist.getId() != null) {
                    preparedStatement = connection.prepareStatement(UPDATE);
                    preparedStatement.setInt(3, playlist.getId());
                } else {
                    preparedStatement = connection.prepareStatement(CREATE);
                    preparedStatement.setInt(3, playlist.getUser().getId());
                }
                preparedStatement.setString(1, playlist.getPlaylistName());
                preparedStatement.setBoolean(2, playlist.getVisible());
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
    public boolean delete(Playlist playlist) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            try {
                connection.setAutoCommit(false);
                preparedStatement.setLong(1, playlist.getId());
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
            preparedStatement.setString(1, name);
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

}
