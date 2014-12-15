package project.pa165.musiclibrary.services;

import project.pa165.musiclibrary.dto.SongDto;

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
    void createSong(SongDto songDto);

    /**
     * Delete song by given id.
     *
     * @param id identification of song to be deleted
     */
    void deleteSong(Long id);

    /**
     * Update given song.
     *
     * @param songDto song to be updated
     */
    void updateSong(SongDto songDto);

    /**
     * Get song by given id.
     *
     * @param id identification of song to be found
     * @return found song
     */
    SongDto findSong(Long id);

    /**
     * Get all songs.
     *
     * @return list of all songs
     */
    List<SongDto> getAllSongs();

    /**
     * Get all songs with same title as given parameter.
     *
     * @param title name of song or songs
     * @return return list of all songs that satisfy given parameter
     */
    List<SongDto> findSongByTitle(String title);
}
