/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.pa165.musiclibrary.services;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import project.pa165.musiclibrary.entities.Genre;
import project.pa165.musiclibrary.entities.Song;

/**
 *
 * @author Matus
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class SongManagerImplTest {

    @Autowired
    private SongManager songManager;

    /**
     * Test of createSong method, of class SongManagerImpl.
     */
    @Test
    public void testCreateSong() {
        Song song = createSong("Walk", (short) 1, 200, Genre.ROCK, 320, "test");

        assertNull(song.getId());
        songManager.createSong(song);
        assertNotNull(song.getId());
    }

    public void testCreatedSongProperties() {
        Song song = createSong("Walk", (short) 1, 200, Genre.ROCK, 320, "test");

        assertNull(song.getId());
        songManager.createSong(song);
        assertNotNull(song.getId());

        Long id = song.getId();
        assertNotNull(id);

        Song actual = songManager.findSong(id);
        assertEquals(song.getTitle(), actual.getTitle());
        assertEquals(song.getBitrate(), actual.getBitrate());
        assertEquals(song.getGenre(), actual.getGenre());
        assertEquals(song.getLenght(), actual.getLenght());
        assertEquals(song.getTrackNumber(), actual.getTrackNumber());
        assertEquals(song.getNote(), actual.getNote());
        assertNull(song.getAlbum());
        assertNull(song.getArtist());
    }

    /**
     * Test of deleteSong method, of class SongManagerImpl.
     */
    @Test
    public void testDeleteSong() {
        Song song = createSong("Walk", (short) 1, 200, Genre.ROCK, 320, "test");

        assertNull(song.getId());
        songManager.createSong(song);
        assertNotNull(song.getId());

        songManager.deleteSong(song.getId());
        assertNull(songManager.findSong(song.getId()));
    }

    /**
     * Test of updateSong method, of class SongManagerImpl.
     */
    @Test
    public void testUpdateSong() {
        Song song = createSong("Walk", (short) 1, 200, Genre.ROCK, null, null);

        assertNull(song.getId());
        songManager.createSong(song);
        assertNotNull(song.getId());

        Long id = song.getId();
        Song actual = songManager.findSong(id);
        assertNull(actual.getBitrate());
        assertNull(actual.getNote());

        song.setBitrate(320);
        song.setNote("testestestestest");

        songManager.updateSong(song);
        actual = songManager.findSong(id);

        assertNotNull(actual.getLenght());
        assertNotNull(actual.getNote());

        assertEquals(song, actual);
    }

    /**
     * Test of findSong method, of class SongManagerImpl.
     */
    @Test
    public void testFindSong() {
        Song song = createSong("Walk", (short) 1, 200, Genre.ROCK, 320, "test");

        assertNull(song.getId());
        songManager.createSong(song);
        assertNotNull(song.getId());

        assertNotNull(songManager.findSong(1l));

        assertEquals(song, songManager.findSong(1l));
    }

    /**
     * Test of getAllSongs method, of class SongManagerImpl.
     */
    @Test
    public void testGetAllSongs() {
        Song song1 = createSong("Walk", (short) 1, 200, Genre.ROCK, 320, "test");
        Song song2 = createSong("Arlandria", (short) 2, 300, Genre.ROCK, 128, "test");

        assertNull(song1.getId());
        assertNull(song2.getId());
        songManager.createSong(song1);
        songManager.createSong(song2);
        assertEquals(songManager.getAllSongs().size(), 2);

        List<Song> expected = new ArrayList<>();
        expected.add(song1);
        expected.add(song2);
        List<Song> actual = songManager.getAllSongs();

        assertArrayEquals(expected.toArray(), actual.toArray());
        deepAssert(expected.toArray(), actual.toArray());
    }

    /**
     * Test of findSongByTitle method, of class SongManagerImpl.
     */
    @Test
    public void testFindSongByTitle() {
        Song song1 = createSong("Walk", (short) 1, 200, Genre.ROCK, 320, "test");
        Song song2 = createSong("Arlandria", (short) 2, 300, Genre.ROCK, 128, "test");

        assertNull(song1.getId());
        assertNull(song2.getId());
        songManager.createSong(song1);
        songManager.createSong(song2);

        assertEquals(songManager.getAllSongs().size(), 2);

        List<Song> expected = new ArrayList<>();
        expected.add(song2);
        List<Song> actual = songManager.findSongByTitle("Arl");

        assertArrayEquals(expected.toArray(), actual.toArray());
        deepAssert(expected.toArray(), actual.toArray());
    }

    /**
     * Test of findSongByTitle method, of class SongManagerImpl.
     */
    @Test
    public void testFindSongsByTitle() {
        Song song1 = createSong("wAlk", (short) 1, 200, Genre.ROCK, 320, "test");
        Song song2 = createSong("walking", (short) 2, 300, Genre.ROCK, 128, "test");

        assertNull(song1.getId());
        assertNull(song2.getId());
        songManager.createSong(song1);
        songManager.createSong(song2);

        assertEquals(songManager.getAllSongs().size(), 2);

        List<Song> expected = new ArrayList<>();
        expected.add(song1);
        expected.add(song2);
        List<Song> actual = songManager.findSongByTitle("Walk");

        assertArrayEquals(expected.toArray(), actual.toArray());
        deepAssert(expected.toArray(), actual.toArray());
    }

    /**
     *
     */
    @Test
    public void testFindSongByTitleOnEmptyDb() {
        assertEquals(songManager.findSongByTitle("Unity").size(), 0);
    }

    private Song createSong(String title, Short trackNumber, Integer length,
            Genre genre, Integer bitrate, String note) {
        Song song = new Song();
        song.setTitle(title);
        song.setTrackNumber(trackNumber);
        song.setLenght(length);
        song.setGenre(genre);
        song.setBitrate(bitrate);
        song.setNote(note);
        return song;
    }

    private void deepAssert(Object[] arr1, Object[] arr2) {
        assertEquals(arr1.length, arr2.length);

        for (int i = 0; i < arr1.length; i++) {
            Song song1 = (Song) arr1[i];
            Song song2 = (Song) arr2[i];
            assertEquals(song1.getId(), song2.getId());
            assertEquals(song1.getTitle(), song2.getTitle());
            assertEquals(song1.getBitrate(), song2.getBitrate());
            assertEquals(song1.getGenre(), song2.getGenre());
            assertEquals(song1.getLenght(), song2.getLenght());
            assertEquals(song1.getNote(), song2.getNote());
            assertEquals(song1.getTrackNumber(), song2.getTrackNumber());
        }
    }

}
