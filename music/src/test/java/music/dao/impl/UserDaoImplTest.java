package music.dao.impl;

import by.nyurush.music.dao.DaoHelper;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.dao.impl.TrackDaoImpl;
import by.nyurush.music.dao.impl.UserDaoImpl;
import by.nyurush.music.entity.*;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Date;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UserDaoImplTest {
    private UserDaoImpl userDao;

    @BeforeTest
    public void createDao() throws DaoException {
        DaoHelperFactory daoHelperFactory = new DaoHelperFactory();
        DaoHelper daoHelper = daoHelperFactory.create();
        userDao = daoHelper.createUserDao();
    }

    @Test
    public void findAllPositiveTest() throws DaoException {
        assertTrue(userDao.findAll().size() > 0);
    }
    
    @Test 
    public void findByIdPositiveTest() throws DaoException {
        assertTrue(userDao.findById(2).isPresent());
    }

    @Test
    public void saveAndDeletePositiveTest() throws DaoException {
        User user = new User(null, "krop", "krop", AccountRole.CLIENT, "krop", "krop", "krop@gmail.com", false);
        Integer result = userDao.save(user);
        boolean actual = result != null;
        Assert.assertTrue(actual);
        user.setId(result);
        Assert.assertTrue(userDao.delete(user));
    }

    @Test
    public void updatePositiveTest() throws DaoException {
        User user = new User(2, "user", "user", AccountRole.CLIENT, "Наталья", "Юруш", "natallia.yurush@gmail.com", true);
        Assert.assertNotNull(userDao.save(user));
    }

    @Test
    public void findByLoginPositiveTest() throws DaoException {
        assertTrue(userDao.findByLogin("user").isPresent());
    }

    @Test
    public void findByEmailPositiveTest() throws DaoException {
        assertTrue(userDao.findByEmail("natallia.yurush@gmail.com").isPresent());
    }
}
