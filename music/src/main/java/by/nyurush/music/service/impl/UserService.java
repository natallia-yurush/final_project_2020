package by.nyurush.music.service.impl;

import by.nyurush.music.dao.DaoHelper;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.impl.UserDaoImpl;
import by.nyurush.music.entity.User;
import by.nyurush.music.service.exception.ServiceException;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;

public class UserService {
    private UserDaoImpl userDao;
    private final DaoHelperFactory daoHelperFactory = new DaoHelperFactory();


    public List<User> findAll() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            userDao = daoHelper.createUserDao();
            return userDao.findAll();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<User> findById(Integer id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            userDao = daoHelper.createUserDao();
            return userDao.findById(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public User save(User user) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            userDao = daoHelper.createUserDao();
            if (user.getPassword().length() <= 30)
                user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            return userDao.save(user);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean delete(User user) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            userDao = daoHelper.createUserDao();
            return userDao.delete(user);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<User> findByLogin(String login) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            userDao = daoHelper.createUserDao();
            return userDao.findByLogin(login);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<User> findByEmail(String email) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            userDao = daoHelper.createUserDao();
            return userDao.findByEmail(email);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<User> isUserExist(String login, String password) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            userDao = daoHelper.createUserDao();
            Optional<User> user = userDao.findByLogin(login);
            if (user.isPresent() && BCrypt.checkpw(password, user.get().getPassword())) {
                return user;
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean isFreeLogin(String login) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            userDao = daoHelper.createUserDao();
            return userDao.findByLogin(login).isEmpty();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
