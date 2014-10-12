package project.pa165.musiclibrary.dao;

import java.util.List;
import project.pa165.musiclibrary.entities.Artist;

/**
 * Base interface for operations and queries on Artist entity.
 * 
 * @author Matúš
 */
public interface ArtistDao extends GenericDao<Artist> {

     /**
     * Get all albums with same title as given parameter.
     *
     * @param name      name of artist or artists
     * @return          return list of all artists that satisfy given parameter
     */
    List<Artist> findArtistByName(String name);

}
