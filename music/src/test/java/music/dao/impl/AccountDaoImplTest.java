package music.dao.impl;

import by.nyurush.music.dao.DaoHelper;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.dao.impl.AccountDaoImpl;
import by.nyurush.music.entity.Account;
import by.nyurush.music.entity.AccountRole;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class AccountDaoImplTest {
    private final DaoHelperFactory daoHelperFactory = new DaoHelperFactory();

    @Test
    public void findAllPositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AccountDaoImpl accountDao = daoHelper.createAccountDao();
            boolean actual = accountDao.findAll().size() > 0;
            assertTrue(actual);
        }
    }

    @Test
    public void findByIdPositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AccountDaoImpl accountDao = daoHelper.createAccountDao();
            boolean actual = accountDao.findById(1).isPresent();
            assertTrue(actual);
        }
    }

    @Test
    public void findByIdNegativeTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AccountDaoImpl accountDao = daoHelper.createAccountDao();
            boolean actual = accountDao.findById(-1).isPresent();
            assertFalse(actual);
        }
    }

    @Test
    public void saveAndDeletePositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AccountDaoImpl accountDao = daoHelper.createAccountDao();
            Account account = new Account(null, "krop", "krop", AccountRole.CLIENT);
            Account result = saveAccount(account, accountDao);
            boolean actual = result.getId() != null;
            assertTrue(actual);
            assertTrue(deleteAccount(account, accountDao));
        }
    }

    private Account saveAccount(Account account, AccountDaoImpl accountDao) throws DaoException {
        return accountDao.save(account);
    }

    private boolean deleteAccount(Account account, AccountDaoImpl accountDao) throws DaoException {
        return accountDao.delete(account);
    }

    @Test
    public void findByLoginPositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AccountDaoImpl accountDao = daoHelper.createAccountDao();
            boolean actual = accountDao.findByLogin("admin").isPresent();
            assertTrue(actual);
        }
    }
}
