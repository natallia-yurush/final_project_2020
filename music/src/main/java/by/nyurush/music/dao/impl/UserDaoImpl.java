package by.nyurush.music.dao.impl;

import by.nyurush.music.dao.AbstractDao;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.entity.User;
import by.nyurush.music.service.builder.UserBuilder;
import by.nyurush.music.service.exception.ServiceException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> {
    private static final String FIND_ALL = "SELECT U.*, A.*, C.country_code FROM user U JOIN account A ON U.account_id = A.id JOIN country C ON U.country_code = C.country_code ";
    private static final String FIND_BY_ID = "SELECT U.*, A.* FROM user U JOIN account A ON U.account_id = A.id WHERE U.account_id=?";
    private static final String FIND_BY_LOGIN = "SELECT U.*, A.* FROM user U JOIN account A ON U.account_id = A.id WHERE A.login=?";
    private static final String FIND_BY_EMAIL = "SELECT U.*, A.* FROM user U JOIN account A ON U.account_id = A.id WHERE U.email=?";
    private static final String CREATE_USERS_ACCOUNT = "INSERT INTO account (login, password, role) VALUES (?, ?, ?)";
    private static final String CREATE_USER = "INSERT INTO user (account_id, first_name, last_name, birth_date, email, subscription, " +
            "country_code) VALUES (?, ?, ?, ?, ?, ? ,?)";
    private static final String UPDATE = "UPDATE account A, user U SET A.login=?, A.password=?, A.role=?, U.first_name=?, U.last_name=?, " +
            "U.birth_date=?, U.email=?, U.subscription=?, U.country_code=? WHERE A.id = ? AND U.account_id = ?";
    private static final String DELETE = "DELETE FROM user WHERE account_id=?";

    @Override
    public List<User> findAll() throws DaoException {
        List<User> usersList = new ArrayList<>();
        User user;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                user = new UserBuilder().build(resultSet);
                usersList.add(user);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return usersList;
    }

    @Override
    public Optional<User> findById(Integer id) throws DaoException {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new UserBuilder().build(resultSet);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public boolean save(User user) throws DaoException {
        PreparedStatement preparedStatement = null;
        Integer generatedId = null;
        try {
            try {
                connection.setAutoCommit(false);
                if (user.getId() != null) {
                    preparedStatement = connection.prepareStatement(UPDATE, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setString(1, user.getLogin());
                    preparedStatement.setString(2, user.getPassword());
                    preparedStatement.setString(3, user.getRole().toString());
                    preparedStatement.setString(4, user.getFirstName());
                    preparedStatement.setString(5, user.getLastName());
                    preparedStatement.setDate(6, new Date(user.getBirthDate().getTime()));
                    preparedStatement.setString(7, user.getEmail());
                    preparedStatement.setBoolean(8, user.getSubscription());
                    preparedStatement.setString(9, user.getCountry());
                    preparedStatement.setInt(10, user.getId());
                    preparedStatement.setInt(11, user.getId());
                    preparedStatement.executeUpdate();
                } else {
                    preparedStatement = connection.prepareStatement(CREATE_USERS_ACCOUNT, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setString(1, user.getLogin());
                    preparedStatement.setString(2, user.getPassword());
                    preparedStatement.setString(3, user.getRole().toString());
                    preparedStatement.execute();
                    ResultSet resultSet = preparedStatement.getGeneratedKeys();
                    if (resultSet.next()) {
                        generatedId = resultSet.getInt(1);
                        preparedStatement = connection.prepareStatement(CREATE_USER);
                        preparedStatement.setInt(1, generatedId);
                        preparedStatement.setString(2, user.getFirstName());
                        preparedStatement.setString(3, user.getLastName());
                        preparedStatement.setDate(4, new Date(user.getBirthDate().getTime()));
                        preparedStatement.setString(5, user.getEmail());
                        preparedStatement.setBoolean(6, user.getSubscription());
                        preparedStatement.setString(7, user.getCountry());
                        preparedStatement.execute();
                    } else {
                        connection.rollback();
                        return false;
                    }
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DaoException();
        }
        return true;
    }

    @Override
    public boolean delete(User user) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            try {
                connection.setAutoCommit(false);
                preparedStatement.setLong(1, user.getId());
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return true;
    }

    public Optional<User> findByLogin(String login) throws DaoException {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new UserBuilder().build(resultSet);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(user);
    }

    public Optional<User> findByEmail(String email) throws DaoException {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new UserBuilder().build(resultSet);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(user);
    }
}
