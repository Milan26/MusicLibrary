package project.pa165.musiclibrary.dao;

import org.springframework.stereotype.Repository;
import project.pa165.musiclibrary.entities.Artist;
import project.pa165.musiclibrary.exception.PersistenceException;

import java.util.List;

/**
 * Implementation of ArtistDao.
 *
 * @author Matúš
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

    @Override
    public List<Artist> findArtistByName(final String name) throws PersistenceException {
        try {
            return getEntityManager().createQuery("SELECT a FROM Artist a WHERE lower(a.alias) like lower('%" + name +
                    "%') ", Artist.class).getResultList();
        } catch (Exception ex) {
            throw new PersistenceException(ex);
        }
    }

}
