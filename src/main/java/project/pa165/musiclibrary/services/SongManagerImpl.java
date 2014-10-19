package project.pa165.musiclibrary.services;

import project.pa165.musiclibrary.dao.SongDao;
import project.pa165.musiclibrary.entities.Song;

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
        getSongDao().create(song);
    }

    @Override
    public void deleteSong(final Long id) {
        getSongDao().delete(id);
    }

    @Override
    public void updateSong(final Song song) {
        getSongDao().update(song);
    }

    @Override
    public Song findSong(final Long id) {
        return getSongDao().find(id);
    }

    @Override
    public List<Song> getAllSongs() {
        return getSongDao().getAll();
    }

    @Override
    public List<Song> findSongByTitle(final String title) {
        return getSongDao().findSongByTitle(title);
    }
}
