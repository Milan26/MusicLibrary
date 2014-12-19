package project.pa165.musiclibrary.dao;

import project.pa165.musiclibrary.entities.Artist;

import java.util.List;

/**
 * Base interface for operations and queries on Artist entity.
 *
 * @author Matúš
 */
public interface ArtistDao extends GenericDao<Artist> {

    /**
     * Get all artists with same title as given parameter.
     *
     * @param name name of artist or artists
     * @return return list of all artists that satisfy given parameter
     */
   
}
