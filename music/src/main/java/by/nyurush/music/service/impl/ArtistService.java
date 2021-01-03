package by.nyurush.music.service.impl;

import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.dao.impl.ArtistDaoImpl;
import by.nyurush.music.entity.Artist;
import by.nyurush.music.service.Service;
import by.nyurush.music.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class ArtistService extends Service {
    private final ArtistDaoImpl artistDao;

    public ArtistService(DaoHelperFactory factory) throws ServiceException {
        super(factory);
        artistDao = daoHelper.createArtistDao();
    }

    public List<Artist> findAll() throws ServiceException {
        try {
            return artistDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<Artist> findById(Integer id) throws ServiceException {
        try {
            return artistDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Artist save(Artist artist) throws ServiceException {
        try {
            return artistDao.save(artist);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean delete(Artist artist) throws ServiceException {
        try {
            return artistDao.delete(artist);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Artist> findByName(String name) throws ServiceException {
        try {
            return artistDao.findByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
