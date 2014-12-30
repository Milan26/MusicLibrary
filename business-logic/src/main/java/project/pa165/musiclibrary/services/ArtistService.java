package project.pa165.musiclibrary.services;

import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('CREATE')")
    void createArtist(ArtistDto artistDto);

    /**
     * Delete artist by given id.
     *
     * @param id identification of artist to be deleted
     */
    @PreAuthorize("hasAuthority('DELETE')")
    void deleteArtist(Long id);

    /**
     * Update given artistDto.
     *
     * @param artistDto artistDto to be updated
     */
    @PreAuthorize("hasAuthority('EDIT')")
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
