package by.nyurush.music.dao.impl;

import by.nyurush.music.dao.AbstractDao;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.entity.Album;
import by.nyurush.music.service.builder.AlbumBuilder;
import by.nyurush.music.service.exception.ServiceException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlbumDaoImpl extends AbstractDao<Album> {
    private static final String FIND_ALL = "SELECT Al.*, Ar.*  FROM album Al JOIN artist Ar ON Al.artist_id = Ar.id";
    private static final String FIND_BY_ID = "SELECT Al.*, Ar.*  FROM album Al JOIN artist Ar ON Al.artist_id = Ar.id WHERE Al.id = ?";
    private static final String FIND_BY_NAME = "SELECT Al.*, Ar.*  FROM album Al JOIN artist Ar ON Al.artist_id = Ar.id WHERE Al.name = ?";
    private static final String FIND_BY_YEAR = "SELECT Al.*, Ar.*  FROM album Al JOIN artist Ar ON Al.artist_id = Ar.id WHERE Al.year = ?";
    private static final String UPDATE = "UPDATE album SET name=?, year=?, number_of_likes=?, artist_id=? WHERE id=?";
    private static final String CREATE = "INSERT INTO album (name, year, number_of_likes, artist_id) VALUES (?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM album WHERE id=?";

    @Override
    public List<Album> findAll() throws DaoException {
        List<Album> albumsList = new ArrayList<>();
        Album album;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                album = new AlbumBuilder().build(resultSet);
                albumsList.add(album);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return albumsList;
    }

    @Override
    public Optional<Album> findById(Integer id) throws DaoException {
        Album album = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                album = new AlbumBuilder().build(resultSet);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(album);
    }

    @Override
    public boolean save(Album album) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            try {
                connection.setAutoCommit(false);
                if (album.getId() != null) {
                    preparedStatement = connection.prepareStatement(UPDATE);
                    preparedStatement.setInt(5, album.getId());
                } else {
                    preparedStatement = connection.prepareStatement(CREATE);
                }
                preparedStatement.setString(1, album.getAlbumName());
                preparedStatement.setInt(2, album.getYear());
                preparedStatement.setInt(3, album.getNumberOfLikes());
                preparedStatement.setInt(4, album.getArtist().getId());
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
    public boolean delete(Album album) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            try {
                connection.setAutoCommit(false);
                preparedStatement.setLong(1, album.getId());
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

    public Optional<Album> findByName(String name) throws DaoException {
        Album album = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                album = new AlbumBuilder().build(resultSet);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(album);
    }

    public Optional<Album> findByYear(String year) throws DaoException {
        Album album = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_YEAR)) {
            preparedStatement.setString(1, year);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                album = new AlbumBuilder().build(resultSet);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(album);
    }
}
