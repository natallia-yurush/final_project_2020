package music.dao.impl;

import by.nyurush.music.dao.DaoHelper;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.dao.impl.AlbumDaoImpl;
import by.nyurush.music.dao.impl.TrackDaoImpl;
import by.nyurush.music.entity.Album;
import by.nyurush.music.entity.Track;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TrackDaoImplTest {
    private final DaoHelperFactory daoHelperFactory = new DaoHelperFactory();

    @Test
    public void findAllPositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TrackDaoImpl trackDao = daoHelper.createTrackDao();
            assertTrue(trackDao.findAll().size() > 0);
        }
    }

    @Test
    public void findByIdPositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TrackDaoImpl trackDao = daoHelper.createTrackDao();
            assertTrue(trackDao.findById(1).isPresent());
        }
    }

    @Test
    public void saveAndDeletePositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TrackDaoImpl trackDao = daoHelper.createTrackDao();
            AlbumDaoImpl albumDao = daoHelper.createAlbumDao();
            Album album = albumDao.findById(1).get();
            Track track = new Track(null, "krop", "krop", 265, "ROCK", album);
            Track result = trackDao.save(track);
            boolean actual = result.getId() != null;
            Assert.assertTrue(actual);
            Assert.assertTrue(trackDao.delete(track));
        }
    }

    @Test
    public void findByNamePositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TrackDaoImpl trackDao = daoHelper.createTrackDao();
            assertTrue(trackDao.findByName("никому", 0, 1000).size() > 0);
        }
    }

    @Test
    public void findByAlbumName() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TrackDaoImpl trackDao = daoHelper.createTrackDao();
            assertTrue(trackDao.findByAlbumName("синоптик").size() > 0);
        }
    }

    @Test
    public void findByGenre() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TrackDaoImpl trackDao = daoHelper.createTrackDao();
            assertTrue(trackDao.findByGenre("ROCK", 0, 1000).size() > 0);
        }
    }

    @Test
    public void findByArtist() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TrackDaoImpl trackDao = daoHelper.createTrackDao();
            assertTrue(trackDao.findByArtist("nizkiz").size() > 0);
        }
    }

    @Test
    public void findByPlaylistId() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TrackDaoImpl trackDao = daoHelper.createTrackDao();
            assertTrue(trackDao.findByPlaylistId(1, 0, 1000).size() > 0);
        }
    }

    @Test
    public void findByPlaylistName() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TrackDaoImpl trackDao = daoHelper.createTrackDao();
            assertEquals(0, trackDao.findByPlaylistName("воля").size());
        }
    }

}
