package project.pa165.musiclibrary.services;

import project.pa165.musiclibrary.dao.SongDao;
import project.pa165.musiclibrary.entities.Song;
import project.pa165.musiclibrary.exception.PersistenceException;
import project.pa165.musiclibrary.exception.ServiceException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Alex
 */
@Named
@Transactional
public class SongManagerImpl implements SongManager {

    private SongDao songDao;

    public SongDao getSongDao() {
        return songDao;
    }

    @Inject
    public void setSongDao(SongDao songDao) {
        this.songDao = songDao;
    }

    @Override
    public void createSong(final Song song) throws ServiceException {
        try {
            getSongDao().create(song);
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }
    }

    @Override
    public void deleteSong(final Long id) throws ServiceException {
        try {
            getSongDao().delete(id);
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }
    }

    @Override
    public void updateSong(final Song song) throws ServiceException {
        try {
            getSongDao().update(song);
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }
    }

    @Override
    public Song findSong(final Long id) throws ServiceException {
        Song song = null;
        try {
            song = getSongDao().find(id);
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }
        return song;
    }

    @Override
    public List<Song> getAllSongs() throws ServiceException {
        List<Song> allSongs = null;
        try {
            allSongs = getSongDao().getAll();
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }
        return allSongs;
    }

    @Override
    public List<Song> findSongByTitle(final String title) throws ServiceException {
        List<Song> allMatchedSongs = null;
        try {
            allMatchedSongs = getSongDao().findSongByTitle(title);
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }
        return allMatchedSongs;
    }
}
