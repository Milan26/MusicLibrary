package project.pa165.musiclibrary.services;

import project.pa165.musiclibrary.entities.Artist;

import java.util.List;

/**
 * @author Matúš
 */
public interface ArtistManager {
    /**
     * Insert given artist.
     *
     * @param artist artist to be inserted
     */
    void createArtist(Artist artist);

    /**
     * Delete artist by given id.
     *
     * @param id identification of artist to be deleted
     */
    void deleteArtist(Long id);

    /**
     * Update given artist.
     *
     * @param artist artist to be updated
     */
    void updateArtist(Artist artist);

    /**
     * Get artist by given id.
     *
     * @param id identification of artist to be found
     * @return found artist
     */
    Artist findArtist(Long id);

    /**
     * Get all artists.
     *
     * @return list of all artists
     */
    List<Artist> getAllArtists();

    /**
     * Get all artists with same title as given parameter.
     *
     * @param name name of artist or artists
     * @return return list of all artists that satisfy given parameter
     */
    List<Artist> findArtistByName(String name);
}
