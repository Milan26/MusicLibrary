package project.pa165.musiclibrary.dao;

import project.pa165.musiclibrary.entities.Album;
import project.pa165.musiclibrary.exception.PersistenceException;

import javax.inject.Named;
import java.util.List;

/**
 * Implementation of AlbumDao.
 *
 * @author Milan
 */
@Named
public class AlbumDaoImpl extends AbstractGenericDao<Album> implements AlbumDao {

    /**
     * Sets specific type for genericDao.
     */
    public AlbumDaoImpl() {
        super();
        setType(Album.class);
    }

    @Override
    public List<Album> findAlbumByTitle(final String title) throws PersistenceException {
        try {
            return getEntityManager().createQuery("SELECT a FROM Album a WHERE lower(a.title) LIKE lower('%" + title +
                    "%')", Album.class).getResultList();
        } catch (Exception ex) {
            throw new PersistenceException(ex);
        }
    }

}
