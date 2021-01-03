package by.nyurush.music.service.impl;

import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.dao.impl.PlaylistDaoImpl;
import by.nyurush.music.entity.Playlist;
import by.nyurush.music.entity.Track;
import by.nyurush.music.service.Service;
import by.nyurush.music.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class PlaylistService extends Service {
    private final PlaylistDaoImpl playlistDao;

    public PlaylistService(DaoHelperFactory factory) throws ServiceException {
        super(factory);
        playlistDao = daoHelper.createPlaylistDao();
    }

    public List<Playlist> findAll() throws ServiceException {
        try {
            return playlistDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<Playlist> findBuId(Integer id) throws ServiceException {
        try {
            return playlistDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Playlist save(Playlist playlist) throws ServiceException {
        try {
            return playlistDao.save(playlist);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean delete(Playlist playlist) throws ServiceException {
        try {
            return playlistDao.delete(playlist);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Playlist> findByName(String name) throws ServiceException {
        try {
            return playlistDao.findByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean addTrack(Playlist playlist, Track track) throws ServiceException {
        try {
            return playlistDao.addTrack(playlist, track);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean deleteTrack(Playlist playlist, Track track) throws ServiceException {
        try {
            return playlistDao.deleteTrack(playlist, track);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
