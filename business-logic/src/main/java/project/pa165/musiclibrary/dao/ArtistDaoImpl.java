package project.pa165.musiclibrary.dao;

import project.pa165.musiclibrary.entities.Artist;

import javax.inject.Named;
import java.util.List;

/**
 * Implementation of ArtistDao.
 *
 * @author Matúš
 */
@Named
public class ArtistDaoImpl extends AbstractGenericDao<Artist> implements ArtistDao {

    /**
     * Sets specific type for genericDao.
     */
    public ArtistDaoImpl() {
        super();
        setType(Artist.class);
    }
}
