package by.nyurush.music.service;

import by.nyurush.music.dao.DaoHelper;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.service.exception.ServiceException;

public abstract class Service {
    protected final DaoHelper daoHelper;

    public Service() throws ServiceException {
        try {
            daoHelper = new DaoHelperFactory().create();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }


        /*try (DaoHelper daoHelper = new DaoHelperFactory().create()){
            //userDao = daoHelper.createUserDao();
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }


    public Service(DaoHelperFactory factory) throws ServiceException {
        try {
            daoHelper = factory.create();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }


}
