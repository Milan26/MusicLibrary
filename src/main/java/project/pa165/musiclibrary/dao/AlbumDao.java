package project.pa165.musiclibrary.dao;

import project.pa165.musiclibrary.entities.Album;

import java.util.List;

/**
 * Base interface for operations and queries on Album entity.
 * 
 * @author Milan
 */
public interface AlbumDao extends GenericDao<Album> {

    /**
     * Get all albums with same title as given parameter.
     *
     * @param title     name of album or albums
     * @return          return list of all albums that satisfy given parameter
     */
    List<Album> findAlbumByTitle(String title);
}
