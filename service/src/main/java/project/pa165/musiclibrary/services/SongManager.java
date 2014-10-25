package project.pa165.musiclibrary.services;

import project.pa165.musiclibrary.entities.Song;

import java.util.List;

/**
 * @author Alex
 */
public interface SongManager {
    /**
     * Insert given song.
     *
     * @param song song to be inserted
     */
    void createSong(Song song);

    /**
     * Delete song by given id.
     *
     * @param id identification of song to be deleted
     */
    void deleteSong(Long id);

    /**
     * Update given song.
     *
     * @param song song to be updated
     */
    void updateSong(Song song);

    /**
     * Get song by given id.
     *
     * @param id identification of song to be found
     * @return found song
     */
    Song findSong(Long id);

    /**
     * Get all songs.
     *
     * @return list of all songs
     */
    List<Song> getAllSongs();

    /**
     * Get all songs with same title as given parameter.
     *
     * @param title name of song or songs
     * @return return list of all songs that satisfy given parameter
     */
    List<Song> findSongByTitle(String title);
}
