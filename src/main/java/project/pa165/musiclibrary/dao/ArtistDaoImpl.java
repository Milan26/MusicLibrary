package project.pa165.musiclibrary.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import project.pa165.musiclibrary.entities.Artist;

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
    public List<Artist> findArtistByName(final String name) {
        return getCurrentSession()
                .createQuery("SELECT a FROM Artist a WHERE lower(a.name) like lower('%" + name + "%') ")
                .list();
    }

}
