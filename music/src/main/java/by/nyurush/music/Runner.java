package by.nyurush.music;

import by.nyurush.music.dao.DaoHelper;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.dao.impl.AccountDaoImpl;

public class Runner {
    public static void main(String[] args) throws DaoException {
        DaoHelperFactory daoHelperFactory = new DaoHelperFactory();
        DaoHelper daoHelper = daoHelperFactory.create();
        AccountDaoImpl accountDao = daoHelper.createAccountDao();
        System.out.println(accountDao.findAll().toString());
    }
}


