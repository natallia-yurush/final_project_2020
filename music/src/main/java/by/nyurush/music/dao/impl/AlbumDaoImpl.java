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
    private static final String FIND_ALL = "SELECT Al.id, Al.name, Al.year, Al.number_of_likes, artist.id, artist.name " +
            "FROM album Al JOIN artist ON Al.artist_id = artist.id";
    private static final String FIND_BY_ID = "SELECT Al.id, Al.name, Al.year, Al.number_of_likes, artist.id, artist.name  " +
            "FROM album Al JOIN artist ON Al.artist_id = artist.id WHERE Al.id = ?";
    private static final String FIND_BY_NAME = "SELECT Al.id, Al.name, Al.year, Al.number_of_likes, artist.id, artist.name  " +
            "FROM album Al JOIN artist ON Al.artist_id = artist.id WHERE Al.name LIKE ?";
    private static final String FIND_BY_YEAR = "SELECT Al.id, Al.name, Al.year, Al.number_of_likes, artist.id, artist.name  " +
            "FROM album Al JOIN artist ON Al.artist_id = artist.id WHERE Al.year = ?";
    private static final String UPDATE = "UPDATE album SET name=?, year=?, number_of_likes=?, artist_id=? WHERE id=?";
    private static final String CREATE = "INSERT INTO album (name, year, number_of_likes, artist_id) VALUES (?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM album WHERE id=?";

    public AlbumDaoImpl(Connection connection) {
        super(connection);
    }

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
    public Album save(Album album) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            try {
                connection.setAutoCommit(false);
                if (album.getId() != null) {
                    preparedStatement = connection.prepareStatement(UPDATE, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setInt(5, album.getId());
                } else {
                    preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
                }
                preparedStatement.setString(1, album.getAlbumName());
                preparedStatement.setInt(2, album.getYear());
                preparedStatement.setInt(3, album.getNumberOfLikes());
                preparedStatement.setInt(4, album.getArtist().getId());
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    album.setId(resultSet.getInt(1));
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
        return album;
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

    public List<Album> findByName(String name) throws DaoException {
        List<Album> albumsList = new ArrayList<>();
        Album album = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME)) {
            preparedStatement.setString(1, "%" + name + "%");
            //preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                album = new AlbumBuilder().build(resultSet);
                albumsList.add(album);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return albumsList;
    }

    public List<Album> findByYear(Integer year) throws DaoException {
        List<Album> albumsList = new ArrayList<>();
        Album album = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_YEAR)) {
            preparedStatement.setInt(1, year);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                album = new AlbumBuilder().build(resultSet);
                albumsList.add(album);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return albumsList;
    }
}
