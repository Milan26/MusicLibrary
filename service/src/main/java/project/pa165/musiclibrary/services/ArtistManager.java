package project.pa165.musiclibrary.services;

import project.pa165.musiclibrary.dto.ArtistDto;
import project.pa165.musiclibrary.exception.ServiceException;

import java.util.List;


/**
 * @author Matúš
 */
public interface ArtistManager {
    /**
     * Insert given artistDto.
     *
     * @param artistDto artistDto to be inserted
     */
    void createArtist(ArtistDto artistDto) throws ServiceException;

    /**
     * Delete artist by given id.
     *
     * @param id identification of artist to be deleted
     */
    void deleteArtist(Long id) throws ServiceException;

    /**
     * Update given artistDto.
     *
     * @param artistDto artistDto to be updated
     */
    void updateArtist(ArtistDto artistDto) throws ServiceException;

    /**
     * Get artist by given id.
     *
     * @param id identification of artist to be found
     * @return found artist
     */
    ArtistDto findArtist(Long id) throws ServiceException;

    /**
     * Get all artists.
     *
     * @return list of all artists
     */
    List<ArtistDto> getAllArtists() throws ServiceException;

    /**
     * Get all artists with same title as given parameter.
     *
     * @param name name of artist or artists
     * @return return list of all artists that satisfy given parameter
     */
    List<ArtistDto> findArtistByName(String name) throws ServiceException;
}
