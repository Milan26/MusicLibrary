package project.pa165.musiclibrary.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import project.pa165.musiclibrary.entities.Song;

/**
 * Implementation of SongDao.
 * 
 * @author Alex
 */
@Repository
public class SongDaoImpl extends AbstractGenericDao<Song> implements SongDao {

    /**
     * Sets specific type for genericDao.
     */
    public SongDaoImpl() {
        super();
        setType(Song.class);
    }

    @Override
    public List<Song> findSongByTitle(final String title) {
        return getCurrentSession()
                .createQuery("SELECT s FROM Song s WHERE lower(s.title) LIKE lower('%" + title + "%')")
                .list();
    }

}
