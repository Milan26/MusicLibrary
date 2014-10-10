package project.pa165.musiclibrary.dao;

import org.springframework.stereotype.Repository;
import project.pa165.musiclibrary.entities.Song;

/**
 * Implementation of SongDao.
 * 
 * @author Milan
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

}
