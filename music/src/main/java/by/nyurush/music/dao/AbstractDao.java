package by.nyurush.music.dao;

import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.dao.pool.ProxyConnection;
import by.nyurush.music.entity.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends Entity> {
    private static final Logger LOGGER = LogManager.getLogger(AbstractDao.class);

    protected Connection connection;

    public AbstractDao (Connection connection) {
        this.connection = connection;
    }

    /*
    public void setConnection(ProxyConnection connection) {
        this.connection = connection;
    }
    */

    public abstract List<T> findAll() throws DaoException;

    public abstract Optional<T> findById(Integer id) throws DaoException;

    public abstract boolean save(T entity) throws DaoException;

    public abstract boolean delete(T entity) throws DaoException;

}
