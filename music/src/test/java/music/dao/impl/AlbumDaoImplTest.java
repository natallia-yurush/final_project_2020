package music.dao.impl;

import by.nyurush.music.dao.DaoHelper;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.dao.impl.AccountDaoImpl;
import by.nyurush.music.dao.impl.AlbumDaoImpl;
import by.nyurush.music.entity.Account;
import by.nyurush.music.entity.AccountRole;
import by.nyurush.music.entity.Album;
import by.nyurush.music.entity.Artist;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class AlbumDaoImplTest {
    private AlbumDaoImpl albumDao;

    @BeforeTest
    public void createDao() throws DaoException {
        DaoHelperFactory daoHelperFactory = new DaoHelperFactory();
        DaoHelper daoHelper = daoHelperFactory.create();
        albumDao = daoHelper.createAlbumDao();
    }

    @Test
    public void findAllPositiveTest() throws DaoException {
        boolean actual = albumDao.findAll().size() > 0;
        assertTrue(actual);
    }

    @Test
    public void findByIdPositiveTest() throws DaoException {
        boolean actual = albumDao.findById(1).isPresent();
        assertTrue(actual);
    }

    @Test
    public void saveAndDeletePositiveTest() throws DaoException {
        Album album = new Album(null, "krop", 2020, 540, new Artist(1, "krop", "fgh"));
        Album result = saveAlbum(album);
        boolean actual = result.getId() != null;
        assertTrue(actual);
        assertTrue(deleteAlbum(album));
    }

    private Album saveAlbum(Album album) throws DaoException {
        return albumDao.save(album);
    }

    private boolean deleteAlbum(Album album) throws DaoException {
        return albumDao.delete(album);
    }

    @Test
    public void findByNamePositiveTest() throws DaoException {
        assertTrue(albumDao.findByName("синоптик").size() > 0);
    }

    @Test
    public void findByYearPositiveTest() throws DaoException {
        assertTrue(albumDao.findByYear(2020).size() > 0);
    }
}
