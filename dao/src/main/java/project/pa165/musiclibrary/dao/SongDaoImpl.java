package project.pa165.musiclibrary.dao;

import project.pa165.musiclibrary.entities.Song;
import project.pa165.musiclibrary.exception.PersistenceException;

import javax.inject.Named;
import java.util.List;

/**
 * Implementation of SongDao.
 *
 * @author Alex
 */
@Named
public class SongDaoImpl extends AbstractGenericDao<Song> implements SongDao {

    /**
     * Sets specific type for genericDao.
     */
    public SongDaoImpl() {
        super();
        setType(Song.class);
    }

    @Override
    public List<Song> findSongByTitle(final String title) throws PersistenceException {
        try {
            return getEntityManager().createQuery("SELECT s FROM Song s WHERE lower(s.title) LIKE lower('%" + title +
                    "%')", Song.class).getResultList();
        } catch (Exception ex) {
            throw new PersistenceException(ex);
        }
    }

}
