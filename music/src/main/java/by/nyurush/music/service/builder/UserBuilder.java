package by.nyurush.music.service.builder;

import by.nyurush.music.entity.Account;
import by.nyurush.music.entity.User;
import by.nyurush.music.service.exception.ServiceException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UserBuilder implements Builder<User>{
    @Override
    public User build(ResultSet resultSet) throws ServiceException {
        Account account = new AccountBuilder().build(resultSet);
        try {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            Date date = resultSet.getDate("birth_date");
            String email = resultSet.getString("email");
            Boolean subscription = resultSet.getBoolean("subscription");
            String country = resultSet.getString("country_code");
            return new User(account.getId(), account.getLogin(), account.getPassword(), account.getRole(), firstName, lastName,
                    date, email, subscription, country);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
