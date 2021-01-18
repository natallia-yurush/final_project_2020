package by.nyurush.music.service.impl;

import by.nyurush.music.dao.DaoHelper;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.impl.AlbumDaoImpl;
import by.nyurush.music.entity.Album;
import by.nyurush.music.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class AlbumService {//} extends Service {
    private AlbumDaoImpl albumDao;
    private final DaoHelperFactory daoHelperFactory = new DaoHelperFactory();

    /*public AlbumService(DaoHelperFactory factory) throws ServiceException {
        super(factory);
        albumDao = daoHelper.createAlbumDao();
    }*/

    public List<Album> findAll() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            albumDao = daoHelper.createAlbumDao();
            return albumDao.findAll();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<Album> findById(Integer id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            albumDao = daoHelper.createAlbumDao();
            return albumDao.findById(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Album save(Album album) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            albumDao = daoHelper.createAlbumDao();
            return albumDao.save(album);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean delete(Album album) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            albumDao = daoHelper.createAlbumDao();
            return albumDao.delete(album);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Album> findByName(String name) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            albumDao = daoHelper.createAlbumDao();
            return albumDao.findByName(name);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Album> findByYear(Integer year) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            albumDao = daoHelper.createAlbumDao();
            return albumDao.findByYear(year);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<Album> findByArtistAndAlbumName(String artist, String albumName) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            albumDao = daoHelper.createAlbumDao();
            return albumDao.findByArtistAndAlbumName(artist, albumName);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Album> findByArtistName(String artistName) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            albumDao = daoHelper.createAlbumDao();
            return albumDao.findByArtistName(artistName);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
