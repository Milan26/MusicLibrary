package project.pa165.musiclibrary.dao;

import java.util.List;
import project.pa165.musiclibrary.entities.Song;

/**
 * Base interface for operations and queries on Song entity.
 * 
 * @author Alex
 */
public interface SongDao extends GenericDao<Song>  {

    /**
     * Get all songs with same title as given parameter.
     *
     * @param title     name of song or songs
     * @return          return list of all songs that satisfy given parameter
     */
    public List<Song> findSongByTitle(String title);
}
