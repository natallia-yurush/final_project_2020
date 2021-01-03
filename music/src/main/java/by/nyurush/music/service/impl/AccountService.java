package by.nyurush.music.service.impl;

import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.dao.impl.AccountDaoImpl;
import by.nyurush.music.entity.Account;
import by.nyurush.music.entity.User;
import by.nyurush.music.service.Service;
import by.nyurush.music.service.exception.ServiceException;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;

public class AccountService extends Service {
    //private final DaoHelper daoHelper;
    private final AccountDaoImpl accountDao;

    public AccountService(DaoHelperFactory factory) throws ServiceException {
        super(factory);
        accountDao = daoHelper.createAccountDao();
    }

/*
    public AccountService (DaoHelperFactory factory) throws ServiceException {
        try {
            daoHelper = factory.create();
            accountDao = daoHelper.createAccountDao();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
*/

    public List<Account> findAll() throws ServiceException {
        try {
            return accountDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<Account> findById(Integer id) throws ServiceException {
        try {
            return accountDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Account save(Account account) throws ServiceException {
        //TODO: при регистрации должна быть проверка, существует ли данный логин!
        try {
            account.setPassword(BCrypt.hashpw(account.getPassword(), BCrypt.gensalt()));
            return accountDao.save(account);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean delete(Account account) throws ServiceException {
        try {
            return accountDao.delete(account);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<Account> findByLogin(String login) throws ServiceException {
        try {
            return accountDao.findByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<Account> isAccountExist(String login, String password) throws ServiceException {
        try {
            //TODO зашифровать пароль
            Optional<Account> account = accountDao.findByLogin(login);
            if(account.isPresent() && BCrypt.checkpw(password, account.get().getPassword())) {
                return account;
            } else {
                return Optional.empty();
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

}
