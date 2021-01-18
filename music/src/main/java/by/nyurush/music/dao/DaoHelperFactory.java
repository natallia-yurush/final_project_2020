package by.nyurush.music.dao;

import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.dao.pool.ConnectionPool;

public class DaoHelperFactory {

    public DaoHelper create() throws DaoException {
        /*try (DaoHelper daoHelper = new DaoHelper(ConnectionPool.getInstance())) {
            return daoHelper;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;*/
        return new DaoHelper(ConnectionPool.getInstance());
    }
}
