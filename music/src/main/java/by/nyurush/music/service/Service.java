package by.nyurush.music.service;

import by.nyurush.music.dao.DaoHelper;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.service.exception.ServiceException;

public abstract class Service {
    protected final DaoHelper daoHelper;

    public Service(DaoHelperFactory factory) throws ServiceException {
        try {
            daoHelper = factory.create();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }


}
