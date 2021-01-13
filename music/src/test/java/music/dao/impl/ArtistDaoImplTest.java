package music.dao.impl;

import by.nyurush.music.dao.DaoHelper;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.dao.impl.AlbumDaoImpl;
import by.nyurush.music.dao.impl.ArtistDaoImpl;
import by.nyurush.music.entity.Album;
import by.nyurush.music.entity.Artist;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class ArtistDaoImplTest {
    private ArtistDaoImpl artistDao;

    @BeforeTest
    public void createDao() throws DaoException {
        DaoHelperFactory daoHelperFactory = new DaoHelperFactory();
        DaoHelper daoHelper = daoHelperFactory.create();
        artistDao = daoHelper.createArtistDao();
    }

    @Test
    public void findAllPositiveTest() throws DaoException {
        assertTrue(artistDao.findAll().size() > 0);
    }

    @Test
    public void findByIdPositiveTest() throws DaoException {
        assertTrue(artistDao.findById(1).isPresent());
    }

    @Test
    public void saveAndDeletePositiveTest() throws DaoException {
        Artist artist = new Artist(null, "krop", "fghj");
        Artist result = artistDao.save(artist);
        boolean actual = result.getId() != null;
        Assert.assertTrue(actual);
        Assert.assertTrue(artistDao.delete(artist));
    }

    @Test
    public void findByNamePositiveTest() throws DaoException {
        assertTrue(artistDao.findByName("nizkiz").size() > 0);
    }
}
