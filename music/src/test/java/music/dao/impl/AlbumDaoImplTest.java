package music.dao.impl;

import by.nyurush.music.dao.DaoHelper;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.dao.impl.AlbumDaoImpl;
import by.nyurush.music.entity.Album;
import by.nyurush.music.entity.Artist;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class AlbumDaoImplTest {
    private final DaoHelperFactory daoHelperFactory = new DaoHelperFactory();

    @Test
    public void findAllPositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AlbumDaoImpl albumDao = daoHelper.createAlbumDao();
            boolean actual = albumDao.findAll().size() > 0;
            assertTrue(actual);
        }
    }

    @Test
    public void findByIdPositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AlbumDaoImpl albumDao = daoHelper.createAlbumDao();
            boolean actual = albumDao.findById(1).isPresent();
            assertTrue(actual);
        }
    }

    @Test
    public void saveAndDeletePositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AlbumDaoImpl albumDao = daoHelper.createAlbumDao();
            Album album = new Album(null, "krop", 2020, 540, new Artist(1, "krop", "fgh"));
            Album result = saveAlbum(album);
            boolean actual = result.getId() != null;
            assertTrue(actual);
            assertTrue(deleteAlbum(album));
        }
    }

    private Album saveAlbum(Album album) throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AlbumDaoImpl albumDao = daoHelper.createAlbumDao();
            return albumDao.save(album);
        }
    }

    private boolean deleteAlbum(Album album) throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AlbumDaoImpl albumDao = daoHelper.createAlbumDao();
            return albumDao.delete(album);
        }
    }

    @Test
    public void findByNamePositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AlbumDaoImpl albumDao = daoHelper.createAlbumDao();
            assertTrue(albumDao.findByName("синоптик").size() > 0);
        }
    }

    @Test
    public void findByYearPositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AlbumDaoImpl albumDao = daoHelper.createAlbumDao();
            assertTrue(albumDao.findByYear(2020).size() > 0);
        }
    }
}
