package project.pa165.musiclibrary.services;

import project.pa165.musiclibrary.dto.ArtistDto;

import java.util.List;


/**
 * @author Matúš
 */
public interface ArtistService {
    /**
     * Insert given artistDto.
     *
     * @param artistDto artistDto to be inserted
     */
    void createArtist(ArtistDto artistDto);

    /**
     * Delete artist by given id.
     *
     * @param id identification of artist to be deleted
     */
    void deleteArtist(Long id);

    /**
     * Update given artistDto.
     *
     * @param artistDto artistDto to be updated
     */
    void updateArtist(ArtistDto artistDto);

    /**
     * Get artist by given id.
     *
     * @param id identification of artist to be found
     * @return found artist
     */
    ArtistDto findArtist(Long id);

    /**
     * Get all artists.
     *
     * @return list of all artists
     */
    List<ArtistDto> getAllArtists();
}
