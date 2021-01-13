package by.nyurush.music.service.impl;

import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.dao.impl.TrackDaoImpl;
import by.nyurush.music.entity.Track;
import by.nyurush.music.service.Service;
import by.nyurush.music.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class TrackService extends Service {
    private final TrackDaoImpl trackDao;

    public TrackService(DaoHelperFactory factory) throws ServiceException {
        super(factory);
        trackDao = daoHelper.createTrackDao();
    }

    public List<Track> findAll() throws ServiceException {
        try {
            return trackDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<Track> findById(Integer id) throws ServiceException {
        try {
            return trackDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Track save(Track track) throws ServiceException {
        try {
            return trackDao.save(track);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean delete(Track track) throws ServiceException {
        try {
            return trackDao.delete(track);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Track> findByName(String name) throws ServiceException {
        try {
            return trackDao.findByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Track> findByAlbumName(String name) throws ServiceException {
        try {
            return trackDao.findByAlbumName(name);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Track> findByGenre(String genre) throws ServiceException {
        try {
            return trackDao.findByGenre(genre);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Track> findByArtist(String name) throws ServiceException {
        try {
            return trackDao.findByArtist(name);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Track> findByPlaylistId(Integer playlistId) throws ServiceException {
        try {
            return trackDao.findByPlaylistId(playlistId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Track> findByPlaylistName(String name) throws ServiceException {
        try {
            return trackDao.findByPlaylistName(name);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<String> findAllGenres() throws ServiceException {
        try {
            return trackDao.findAllGenres();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
