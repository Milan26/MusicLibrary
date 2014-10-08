package project.pa165.musiclibrary.dao;

import java.util.List;
import project.pa165.musiclibrary.entities.Album;

public interface AlbumDao extends GenericDao<Album> {

    /**
     * 
     * @param title
     * @return 
     */
    List<Album> findAlbumByTitle(String title);
}
