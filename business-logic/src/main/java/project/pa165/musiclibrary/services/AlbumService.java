package project.pa165.musiclibrary.services;

import org.springframework.security.access.prepost.PreAuthorize;
import project.pa165.musiclibrary.dto.AlbumDto;

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
    @PreAuthorize("hasAuthority('CREATE')")
    void createAlbum(AlbumDto albumDto);

    /**
     * Delete album by given id.
     *
     * @param id identification of album to be deleted
     */
    @PreAuthorize("hasAuthority('DELETE')")
    void deleteAlbum(Long id);

    /**
     * Update given albumDto.
     *
     * @param albumDto albumDto to be updated
     */
    @PreAuthorize("hasAuthority('EDIT')")
    void updateAlbum(AlbumDto albumDto);

    /**
     * Get album by given id.
     *
     * @param id identification of album to be found
     * @return found album
     */
    AlbumDto findAlbum(Long id);

    /**
     * Get all albums.
     *
     * @return list of all albums
     */
    List<AlbumDto> getAllAlbums();

    /**
     * Get all albums with same title as given parameter.
     *
     * @param title name of album or albums
     * @return return list of all albums that satisfy given parameter
     */
    List<AlbumDto> findAlbumByTitle(String title);
}
