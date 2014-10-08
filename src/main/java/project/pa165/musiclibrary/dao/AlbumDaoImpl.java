package project.pa165.musiclibrary.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import project.pa165.musiclibrary.entities.Album;

@Repository
public class AlbumDaoImpl extends GenericDaoImpl<Album> implements AlbumDao {

    public AlbumDaoImpl() {
        setType(Album.class);
    }

    @Override
    public List<Album> findAlbumByTitle(String title) {
        return getCurrentSession()
                .createQuery("SELECT a FROM Album a WHERE a.title = :title ")
                .setParameter("title", title)
                .list();
    }

}
