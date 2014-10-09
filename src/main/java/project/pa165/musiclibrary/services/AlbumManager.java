package project.pa165.musiclibrary.services;

import java.util.List;
import project.pa165.musiclibrary.entities.Album;

/**
 *
 * @author Milan
 */
public interface AlbumManager {

    /**
     * Insert given album.
     *
     * @param album     album to be inserted
     */
    void createAlbum(Album album);

    /**
     * Delete album by given id.
     *
     * @param id        identification of album to be deleted
     */
    void deleteAlbum(Long id);

    /**
     * Update given album.
     *
     * @param album     album to be updated
     */
    void updateAlbum(Album album);

    /**
     * Get album by given id.
     *
     * @param id        identification of album to be found
     * @return          found album
     */
    Album findAlbum(Long id);

    /**
     * Get all albums.
     *
     * @return          list of all albums
     */
    List<Album> getAllAlbums();

    /**
     * Get all albums with same title as given parameter.
     *
     * @param title     name of album or albums
     * @return          return list of all albums that satisfy given parameter
     */
    List<Album> findAlbumByTitle(String title);
}
