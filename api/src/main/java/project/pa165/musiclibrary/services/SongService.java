package project.pa165.musiclibrary.services;

import project.pa165.musiclibrary.dto.SongDto;
import project.pa165.musiclibrary.exception.ServiceException;

import java.util.List;

/**
 * @author Alex
 */
public interface SongService {
    /**
     * Insert given song.
     *
     * @param songDto song to be inserted
     */
    void createSong(SongDto songDto) throws ServiceException;

    /**
     * Delete song by given id.
     *
     * @param id identification of song to be deleted
     */
    void deleteSong(Long id) throws ServiceException;

    /**
     * Update given song.
     *
     * @param songDto song to be updated
     */
    void updateSong(SongDto songDto) throws ServiceException;

    /**
     * Get song by given id.
     *
     * @param id identification of song to be found
     * @return found song
     */
    SongDto findSong(Long id) throws ServiceException;

    /**
     * Get all songs.
     *
     * @return list of all songs
     */
    List<SongDto> getAllSongs() throws ServiceException;

    /**
     * Get all songs with same title as given parameter.
     *
     * @param title name of song or songs
     * @return return list of all songs that satisfy given parameter
     */
    List<SongDto> findSongByTitle(String title) throws ServiceException;
}
