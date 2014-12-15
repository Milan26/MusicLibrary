package project.pa165.musiclibrary.services;

import project.pa165.musiclibrary.dto.AlbumDto;
import project.pa165.musiclibrary.exception.ServiceException;

import java.util.List;

/**
 * @author Milan
 */
public interface AlbumService {

    /**
     * Insert given albumDto.
     *
     * @param albumDto albumDto to be inserted
     */
    void createAlbum(AlbumDto albumDto) throws ServiceException;

    /**
     * Delete album by given id.
     *
     * @param id identification of album to be deleted
     */
    void deleteAlbum(Long id) throws ServiceException;

    /**
     * Update given albumDto.
     *
     * @param albumDto albumDto to be updated
     */
    void updateAlbum(AlbumDto albumDto) throws ServiceException;

    /**
     * Get album by given id.
     *
     * @param id identification of album to be found
     * @return found album
     */
    AlbumDto findAlbum(Long id) throws ServiceException;

    /**
     * Get all albums.
     *
     * @return list of all albums
     */
    List<AlbumDto> getAllAlbums() throws ServiceException;

    /**
     * Get all albums with same title as given parameter.
     *
     * @param title name of album or albums
     * @return return list of all albums that satisfy given parameter
     */
    List<AlbumDto> findAlbumByTitle(String title) throws ServiceException;
}
