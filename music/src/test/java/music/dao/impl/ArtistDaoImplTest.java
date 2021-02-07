package music.dao.impl;

import by.nyurush.music.dao.DaoHelper;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.dao.impl.ArtistDaoImpl;
import by.nyurush.music.entity.Artist;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class ArtistDaoImplTest {
    private final DaoHelperFactory daoHelperFactory = new DaoHelperFactory();

    @Test
    public void findAllPositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ArtistDaoImpl artistDao = daoHelper.createArtistDao();
            assertTrue(artistDao.findAll().size() > 0);
        }
    }

    @Test
    public void findByIdPositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ArtistDaoImpl artistDao = daoHelper.createArtistDao();
            assertTrue(artistDao.findById(1).isPresent());
        }
    }

    @Test
    public void saveAndDeletePositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ArtistDaoImpl artistDao = daoHelper.createArtistDao();
            Artist artist = new Artist(null, "krop", "fghj");
            Artist result = artistDao.save(artist);
            boolean actual = result.getId() != null;
            Assert.assertTrue(actual);
            Assert.assertTrue(artistDao.delete(artist));
        }
    }

    @Test
    public void findByNamePositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ArtistDaoImpl artistDao = daoHelper.createArtistDao();
            assertTrue(artistDao.findByName("nizkiz").size() > 0);
        }
    }
}
