package project.pa165.musiclibrary.dao;

import project.pa165.musiclibrary.entities.Song;
import project.pa165.musiclibrary.exception.DaoException;

import java.util.List;

/**
 * Base interface for operations and queries on Song entity.
 *
 * @author Alex
 */
public interface SongDao extends GenericDao<Song> {

    /**
     * Get all songs with same title as given parameter.
     *
     * @param title name of song or songs
     * @return return list of all songs that satisfy given parameter
     */
    List<Song> findSongByTitle(String title) throws DaoException;
}
