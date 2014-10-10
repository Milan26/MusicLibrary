package project.pa165.musiclibrary.dao;

import org.springframework.stereotype.Repository;
import project.pa165.musiclibrary.entities.Artist;

/**
 * Implementation of ArtistDao.
 * 
 * @author Milan
 */
@Repository
public class ArtistDaoImpl extends AbstractGenericDao<Artist> implements ArtistDao {

    /**
     * Sets specific type for genericDao.
     */
    public ArtistDaoImpl() {
        super();
        setType(Artist.class);
    }

}
