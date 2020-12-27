package music.dao.impl;

import by.nyurush.music.dao.DaoHelper;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.dao.impl.TrackDaoImpl;
import by.nyurush.music.entity.Album;
import by.nyurush.music.entity.Track;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

public class TrackDaoImplTest {
    private TrackDaoImpl trackDao;

    @BeforeTest
    public void createDao() throws DaoException {
        DaoHelperFactory daoHelperFactory = new DaoHelperFactory();
        DaoHelper daoHelper = daoHelperFactory.create();
        trackDao = daoHelper.createTrackDao();
    }

    @Test
    public void findAllPositiveTest() throws DaoException {
        assertTrue(trackDao.findAll().size() > 0);
    }

    @Test
    public void findByIdPositiveTest() throws DaoException {
        assertTrue(trackDao.findById(1).isPresent());
    }

    @Test
    public void saveAndDeletePositiveTest() throws DaoException {
        Album album = new Album(1);
        Track track = new Track(null, "krop", "krop", 265, "ROCK", album);
        Integer result = trackDao.save(track);
        boolean actual = result != null;
        Assert.assertTrue(actual);
        track.setId(result);
        Assert.assertTrue(trackDao.delete(track));
    }

    @Test
    public void findByNamePositiveTest() throws DaoException {
        assertTrue(trackDao.findByName("никому").size() > 0);
    }

    @Test
    public void findByAlbumName() throws DaoException {
        assertTrue(trackDao.findByAlbumName("синоптик").size() > 0);
    }

    @Test
    public void findByGenre() throws DaoException {
        assertTrue(trackDao.findByGenre("ROCK").size() > 0);
    }

    @Test
    public void findByArtist() throws DaoException {
        assertTrue(trackDao.findByArtist("nizkiz").size() > 0);
    }

    @Test
    public void findByPlaylistId() throws DaoException {
        assertTrue(trackDao.findByPlaylistId(1).size() > 0);
    }

    @Test
    public void findByPlaylistName() throws DaoException {
        assertEquals(0, trackDao.findByPlaylistName("воля").size());
    }

}
