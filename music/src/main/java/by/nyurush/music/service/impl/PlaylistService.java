package by.nyurush.music.service.impl;

import by.nyurush.music.dao.DaoHelper;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.impl.PlaylistDaoImpl;
import by.nyurush.music.entity.Playlist;
import by.nyurush.music.entity.Track;
import by.nyurush.music.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class PlaylistService {//} extends Service {
    private PlaylistDaoImpl playlistDao;
    private final DaoHelperFactory daoHelperFactory = new DaoHelperFactory();

   /* public PlaylistService(DaoHelperFactory factory) throws ServiceException {
        super(factory);
        playlistDao = daoHelper.createPlaylistDao();
    }*/

    public List<Playlist> findAll() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            playlistDao = daoHelper.createPlaylistDao();
            return playlistDao.findAll();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<Playlist> findBuId(Integer id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            playlistDao = daoHelper.createPlaylistDao();
            return playlistDao.findById(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Playlist save(Playlist playlist) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            playlistDao = daoHelper.createPlaylistDao();
            return playlistDao.save(playlist);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean delete(Playlist playlist) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            playlistDao = daoHelper.createPlaylistDao();
            return playlistDao.delete(playlist);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Playlist> findByName(String name) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            playlistDao = daoHelper.createPlaylistDao();
            return playlistDao.findByName(name);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean addTrack(Playlist playlist, Track track) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            playlistDao = daoHelper.createPlaylistDao();
            return playlistDao.addTrack(playlist, track);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean deleteTrack(Playlist playlist, Track track) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            playlistDao = daoHelper.createPlaylistDao();
            return playlistDao.deleteTrack(playlist, track);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Playlist> findByUserId(Integer userId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            playlistDao = daoHelper.createPlaylistDao();
            return playlistDao.findByUserId(userId);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<Playlist> findByNameAndUserId(String name, Integer userId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            playlistDao = daoHelper.createPlaylistDao();
            return playlistDao.findByNameAndUserId(name, userId);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
