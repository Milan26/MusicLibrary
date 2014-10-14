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

    @Inject
    private SongDao songDao;

    @Override
    public void createSong(final Song song) {
        songDao.create(song);
    }

    @Override
    public void deleteSong(final Long id) {
        songDao.delete(id);
    }

    @Override
    public void updateSong(final Song song) {
        songDao.update(song);
    }

    @Override
    public Song findSong(final Long id) {
        return songDao.find(id);
    }

    @Override
    public List<Song> getAllSongs() {
        return songDao.getAll();
    }

    @Override
    public List<Song> findSongByTitle(final String title) {
        return songDao.findSongByTitle(title);
    }
}
