package by.nyurush.music.dao;

import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.entity.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends Entity> {

    protected Connection connection;

    public AbstractDao (Connection connection) {
        this.connection = connection;
    }

    public abstract List<T> findAll() throws DaoException;

    public abstract Optional<T> findById(Integer id) throws DaoException;

    public abstract T save(T entity) throws DaoException;

    public abstract boolean delete(T entity) throws DaoException;

    protected boolean deleteObject(T t, String query) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try {
                connection.setAutoCommit(false);
                preparedStatement.setInt(1, t.getId());
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
