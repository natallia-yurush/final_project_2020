package by.nyurush.music.service.builder;

import by.nyurush.music.entity.Entity;
import by.nyurush.music.service.exception.ServiceException;

import java.sql.ResultSet;

public interface Builder <T extends Entity> {
    T build(ResultSet resultSet) throws ServiceException;
}
