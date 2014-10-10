package project.pa165.musiclibrary.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import project.pa165.musiclibrary.entities.Album;

/**
 * Implementation of AlbumDao.
 * 
 * @author Milan
 */
@Repository
public class AlbumDaoImpl extends AbstractGenericDao<Album> implements AlbumDao {

    /**
     * Sets specific type for genericDao.
     */
    public AlbumDaoImpl() {
        super();
        setType(Album.class);
    }

    @Override
    public List<Album> findAlbumByTitle(final String title) {
        return getCurrentSession()
                .createQuery("SELECT a FROM Album a WHERE a.title = :title ")
                .setParameter("title", title)
                .list();
    }

}
