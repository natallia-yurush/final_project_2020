package music.dao.impl;

import by.nyurush.music.dao.DaoHelper;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.dao.impl.PlaylistDaoImpl;
import by.nyurush.music.entity.Playlist;
import by.nyurush.music.entity.Track;
import by.nyurush.music.entity.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class PlaylistDaoImplTest {
    private final DaoHelperFactory daoHelperFactory = new DaoHelperFactory();

    @Test
    public void findAllPositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            PlaylistDaoImpl playlistDao = daoHelper.createPlaylistDao();
            assertTrue(playlistDao.findAll().size() > 0);
        }
    }

    @Test
    public void findByIdPositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            PlaylistDaoImpl playlistDao = daoHelper.createPlaylistDao();
            assertTrue(playlistDao.findById(1).isPresent());
        }
    }

    @Test
    public void saveAndDeletePositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            PlaylistDaoImpl playlistDao = daoHelper.createPlaylistDao();
            User user = new User(2);
            Playlist playlist = new Playlist(null, "krop", false, user);
            Playlist result = playlistDao.save(playlist);
            boolean actual = result.getId() != null;
            Assert.assertTrue(actual);
            Assert.assertTrue(playlistDao.delete(playlist));
        }
    }

    @Test
    public void findByNamePositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            PlaylistDaoImpl playlistDao = daoHelper.createPlaylistDao();
            assertTrue(playlistDao.findByName("воля").size() > 0);
        }
    }

    @Test
    public void addAndDeleteTrack() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            PlaylistDaoImpl playlistDao = daoHelper.createPlaylistDao();
            Playlist playlist = new Playlist(1);
            Track track = new Track(4);
            boolean actual = playlistDao.addTrack(playlist, track);
            Assert.assertTrue(actual);
            Assert.assertTrue(playlistDao.deleteTrack(playlist, track));
        }
    }
}
