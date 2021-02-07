package music.dao.impl;

import by.nyurush.music.dao.DaoHelper;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.dao.impl.AccountDaoImpl;
import by.nyurush.music.dao.impl.UserDaoImpl;
import by.nyurush.music.entity.AccountRole;
import by.nyurush.music.entity.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class UserDaoImplTest {
    private final DaoHelperFactory daoHelperFactory = new DaoHelperFactory();

    @Test
    public void findAllPositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDaoImpl userDao = daoHelper.createUserDao();
            assertTrue(userDao.findAll().size() > 0);
        }
    }

    @Test
    public void findByIdPositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDaoImpl userDao = daoHelper.createUserDao();
            assertTrue(userDao.findById(2).isPresent());
        }
    }

    @Test
    public void saveAndDeletePositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDaoImpl userDao = daoHelper.createUserDao();
            AccountDaoImpl accountDao = daoHelper.createAccountDao();
            User user = new User(null, "krop", "krop", AccountRole.CLIENT, "krop", "krop", "krop@gmail.com", false);
            User result = userDao.save(user);
            boolean actual = result.getId() != null;
            Assert.assertTrue(actual);
            Assert.assertTrue(userDao.delete(user));
            Assert.assertTrue(accountDao.delete(user));
        }
    }

    @Test
    public void updatePositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDaoImpl userDao = daoHelper.createUserDao();
            User user = new User(2, "user", "user", AccountRole.CLIENT, "Наталья", "Юруш", "natallia.yurush@gmail.com", true);
            Assert.assertNotNull(userDao.save(user));
        }
    }

    @Test
    public void findByLoginPositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDaoImpl userDao = daoHelper.createUserDao();
            assertTrue(userDao.findByLogin("user").isPresent());
        }
    }

    @Test
    public void findByEmailPositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDaoImpl userDao = daoHelper.createUserDao();
            assertTrue(userDao.findByEmail("natallia.yurush@gmail.com").isPresent());
        }
    }
}
