package by.nyurush.music.service.impl;

import by.nyurush.music.dao.DaoHelper;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.impl.TrackDaoImpl;
import by.nyurush.music.entity.Track;
import by.nyurush.music.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class TrackService {
    private TrackDaoImpl trackDao;
    private final DaoHelperFactory daoHelperFactory = new DaoHelperFactory();

    public Optional<Track> findById(Integer id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            trackDao = daoHelper.createTrackDao();
            return trackDao.findById(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Track save(Track track) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            trackDao = daoHelper.createTrackDao();
            return trackDao.save(track);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean delete(Track track) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            trackDao = daoHelper.createTrackDao();
            return trackDao.delete(track);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Track> findByName(String name, Integer offset, Integer recordsPerPage) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            trackDao = daoHelper.createTrackDao();
            return trackDao.findByName(name, offset, recordsPerPage);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Track> findByAlbumName(String name) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            trackDao = daoHelper.createTrackDao();
            return trackDao.findByAlbumName(name);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Track> findByGenre(String genre, Integer offset, Integer recordsPerPage) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            trackDao = daoHelper.createTrackDao();
            return trackDao.findByGenre(genre, offset, recordsPerPage);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Track> findByArtist(String name) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            trackDao = daoHelper.createTrackDao();
            return trackDao.findByArtist(name);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Track> findByPlaylistId(Integer playlistId, Integer offset, Integer recordsPerPage) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            trackDao = daoHelper.createTrackDao();
            return trackDao.findByPlaylistId(playlistId, offset, recordsPerPage);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Track> findByPlaylistName(String name) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            trackDao = daoHelper.createTrackDao();
            return trackDao.findByPlaylistName(name);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<String> findAllGenres() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            trackDao = daoHelper.createTrackDao();
            return trackDao.findAllGenres();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Track> findForPage(Integer offset, Integer recordsPerPage) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            trackDao = daoHelper.createTrackDao();
            return trackDao.findForPage(offset, recordsPerPage);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Integer getNoOfRecords() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            trackDao = daoHelper.createTrackDao();
            return trackDao.getNoOfRecords();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Integer getNoOfRecordsByGenre(String genre) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            trackDao = daoHelper.createTrackDao();
            return trackDao.getNoOfRecordsByGenre(genre);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Integer getNoOfRecordsByName(String name) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            trackDao = daoHelper.createTrackDao();
            return trackDao.getNoOfRecordsByName(name);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Integer getNoOfRecordsByPlaylistId(Integer id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            trackDao = daoHelper.createTrackDao();
            return trackDao.getNoOfRecordsByPlaylistId(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
