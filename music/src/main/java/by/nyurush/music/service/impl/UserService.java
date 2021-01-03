package by.nyurush.music.service.impl;

import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.dao.impl.UserDaoImpl;
import by.nyurush.music.entity.User;
import by.nyurush.music.service.Service;
import by.nyurush.music.service.exception.ServiceException;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;

public class UserService extends Service {
    private final UserDaoImpl userDao;// = new DaoHelperFactory().create().createUserDao();

    public UserService(DaoHelperFactory factory) throws ServiceException {
        super(factory);
        userDao = daoHelper.createUserDao();
    }

    public List<User> findAll() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<User> findById(Integer id) throws ServiceException {
        try {
            return userDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public User save(User user) throws ServiceException {
        try {
            //TODO hash
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            return userDao.save(user);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean delete(User user) throws ServiceException {
        try {
            return userDao.delete(user);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<User> findByLogin(String login) throws ServiceException {
        try {
            return userDao.findByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<User> findByEmail(String email) throws ServiceException {
        try {
            return userDao.findByEmail(email);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<User> isUserExist(String login, String password) throws ServiceException {
        try {
            Optional<User> user = userDao.findByLogin(login);
            if(user.isPresent() && BCrypt.checkpw(password, user.get().getPassword())) {
                return user;
            } else {
                return Optional.empty();
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean isFreeLogin(String login) throws ServiceException {
        try {
            return !userDao.findByLogin(login).isPresent();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
