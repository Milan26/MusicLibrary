package project.pa165.musiclibrary.services;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.pa165.musiclibrary.dao.SongDao;
import project.pa165.musiclibrary.entities.Song;

/**
 *
 * @author Alex
 */
@Service
public class SongManagerImpl implements SongManager {

    @Autowired
    private SongDao songDao;

    @Override
    @Transactional
    public void createSong(final Song song) {
        songDao.create(song);
    }

    @Override
    @Transactional
    public void deleteSong(final Long id) {
        songDao.delete(id);
    }

    @Override
    @Transactional
    public void updateSong(final Song song) {
        songDao.update(song);
    }

    @Override
    @Transactional
    public Song findSong(final Long id) {
        return songDao.find(id);
    }

    @Override
    @Transactional
    public List<Song> getAllSongs() {
        return songDao.getAll();
    }

    @Override
    @Transactional
    public List<Song> findSongByTitle(final String title) {
        return songDao.findSongByTitle(title);
    }
}
