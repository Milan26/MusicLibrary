package project.pa165.musiclibrary.services;

import project.pa165.musiclibrary.dao.SongDao;
import project.pa165.musiclibrary.entities.Song;
import project.pa165.musiclibrary.exception.DaoException;

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
    public void createSong(final Song song) {
        try {
            getSongDao().create(song);
        } catch (DaoException e) {

        }
    }

    @Override
    public void deleteSong(final Long id) {
        try {
            getSongDao().delete(id);
        } catch (DaoException e) {

        }
    }

    @Override
    public void updateSong(final Song song) {
        try {
            getSongDao().update(song);
        } catch (DaoException e) {

        }
    }

    @Override
    public Song findSong(final Long id) {
        Song song = null;
        try {
            song = getSongDao().find(id);
        } catch (DaoException e) {

        }
        return song;
    }

    @Override
    public List<Song> getAllSongs() {
        List<Song> allSongs = null;
        try {
            allSongs = getSongDao().getAll();
        } catch (DaoException e) {

        }
        return allSongs;
    }

    @Override
    public List<Song> findSongByTitle(final String title) {
        List<Song> allMatchedSongs = null;
        try {
            allMatchedSongs = getSongDao().findSongByTitle(title);
        } catch (DaoException e) {

        }
        return allMatchedSongs;
    }
}
