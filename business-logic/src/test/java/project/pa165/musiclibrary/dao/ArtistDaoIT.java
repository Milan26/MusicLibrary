package project.pa165.musiclibrary.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import project.pa165.musiclibrary.entities.Artist;
import project.pa165.musiclibrary.util.Genre;
import project.pa165.musiclibrary.entities.Song;
import project.pa165.musiclibrary.exception.PersistenceException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Alex
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext-dao-test.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ArtistDaoIT {

    private Artist artist1;
    private Artist artist2;

    private ArtistDao artistDao;

    public ArtistDao getArtistDao() {
        return artistDao;
    }

    @Autowired
    public void setArtistDao(ArtistDao artistDao) {
        this.artistDao = artistDao;
    }

    @Before
    public void setUp() {
        artist1 = createArtist("Alfa", "Testing artist");
        artist2 = createArtist("Beta", "Bingo JUNIT HALO3");
    }

    @Test
    public void testCreateArtist() throws PersistenceException {
        persist(artist1);
    }

    @Test
    public void testCreatedArtistProperties() throws PersistenceException {
        persist(artist1);

        Artist actual = getArtistDao().find(artist1.getId());
        deepAssert(artist1, actual);
    }

    @Test(expected = PersistenceException.class)
    public void testCreateArtistNull() throws PersistenceException {
        getArtistDao().create(null);
    }

    @Test
    public void testDeleteArtist() throws PersistenceException {
        persist(artist1);

        getArtistDao().delete(artist1.getId());
        assertNull(getArtistDao().find(artist1.getId()));
    }

    @Test(expected = PersistenceException.class)
    public void testDeleteArtistNull() throws PersistenceException {
        getArtistDao().delete(null);
    }

    @Test
    public void testUpdateArtist() throws PersistenceException {
        persist(artist1);

        Artist actual = getArtistDao().find(artist1.getId());
        assertNotEquals("Updated", actual.getAlias());
        assertNotEquals("Updated note", actual.getNote());

        artist1.setAlias("Updated");
        artist1.setNote("Updated note");

        getArtistDao().update(artist1);
        actual = getArtistDao().find(artist1.getId());

        deepAssert(artist1, actual);
    }

    @Test(expected = PersistenceException.class)
    public void testUpdateArtistNull() throws PersistenceException {
        getArtistDao().update(null);
    }

    @Test
    public void testFindArtist() throws PersistenceException {
        persist(artist1);

        Artist actual = getArtistDao().find(artist1.getId());
        assertNotNull(actual);
        assertEquals(artist1, actual);
    }

    @Test(expected = PersistenceException.class)
    public void testFindArtistWithNullId() throws PersistenceException {
        getArtistDao().find(artist1.getId());
    }

    @Test
    public void testFindArtistWithWrongId() throws PersistenceException {
        assertNull(getArtistDao().find(5l));
    }

    @Test
    public void testGetAllArtists() throws PersistenceException {
        persist(artist1);
        persist(artist2);

        List<Artist> expected = Arrays.asList(artist1, artist2);
        assertEquals(expected.size(), 2);
        List<Artist> actual = getArtistDao().getAll();
        assertEquals(actual.size(), 2);

        assertArrayEquals(expected.toArray(), actual.toArray());
        deepAssert(expected.toArray(), actual.toArray());
    }

    @Test
    public void testGetAllArtistsEmptyDb() throws PersistenceException {
        List<Artist> actual = getArtistDao().getAll();
        assertEquals(actual.size(), 0);
    }

    @Test
    public void testFindArtistByNameWithUniqueName() throws PersistenceException {
        persist(artist1);
        persist(artist2);

        artist1.setAlias("I am Unique");
        artist2.setAlias("I am not");
        getArtistDao().update(artist1);
        getArtistDao().update(artist2);

        assertEquals(getArtistDao().getAll().size(), 2);

        List<Artist> expected = Arrays.asList(artist1);
        List<Artist> actual = getArtistDao().findArtistByName("unique");

        assertArrayEquals(expected.toArray(), actual.toArray());
        deepAssert(expected.toArray(), actual.toArray());
    }

    @Test
    public void testFindArtistByNameWithMultipleSameNameArtist() throws PersistenceException {
        persist(artist1);
        persist(artist2);

        artist1.setAlias("I am Unique");
        artist2.setAlias("I am not so UniQuE, or maybe I am");
        getArtistDao().update(artist1);
        getArtistDao().update(artist2);

        assertEquals(getArtistDao().getAll().size(), 2);

        List<Artist> expected = Arrays.asList(artist1, artist2);
        List<Artist> actual = getArtistDao().findArtistByName("unique");

        assertArrayEquals(expected.toArray(), actual.toArray());
        deepAssert(expected.toArray(), actual.toArray());
    }

    @Test
    public void testFindArtistByNameWithAnyMatch() throws PersistenceException {
        persist(artist1);
        persist(artist2);

        assertEquals(getArtistDao().getAll().size(), 2);

        List<Artist> expected = new ArrayList<>();
        List<Artist> actual = getArtistDao().findArtistByName("alhamra");

        assertArrayEquals(expected.toArray(), actual.toArray());
        deepAssert(expected.toArray(), actual.toArray());
    }

    @Test
    public void testFindArtistByNameOnEmptyDb() throws PersistenceException {
        List<Artist> actual = getArtistDao().getAll();
        assertEquals(actual.size(), 0);

        assertEquals(getArtistDao().findArtistByName("Unity").size(), 0);
    }

    @Test
    public void testArtistJdbcMapping() throws PersistenceException {
        Song song1 = createSong("Hey Ho HooHo", (short) 1, 200, Genre.HOLIDAY, 320, "test");
        Song song2 = createSong("Oh yea", (short) 2, 300, Genre.INDIE, 128, "test");
        List<Song> songs = Arrays.asList(song1, song2);
        artist1.setSongs(songs);

        persist(artist1);
        Artist artist = getArtistDao().find(artist1.getId());

        assertEquals(songs.size(), artist.getSongs().size());
        assertArrayEquals(songs.toArray(), artist.getSongs().toArray());
        deepAssert(artist1, artist);
    }

    private void persist(Artist artist) throws PersistenceException {
        assertNull(artist.getId());
        getArtistDao().create(artist);
        assertNotNull(artist.getId());
    }

    private Artist createArtist(String name, String note) {
        Artist artist = new Artist();
        artist.setAlias(name);
        artist.setNote(note);
        return artist;
    }

    private Song createSong(String title, Short trackNumber, Integer length, Genre genre, Integer bitrate, String
            note) {
        Song song = new Song();
        song.setTitle(title);
        song.setTrackNumber(trackNumber);
        song.setDuration(length);
        song.setGenre(genre);
        song.setBitrate(bitrate);
        song.setNote(note);
        return song;
    }

    private void deepAssert(Artist artist1, Artist artist2) {
        assertEquals(artist1.getId(), artist2.getId());
        assertEquals(artist1.getAlias(), artist2.getAlias());
        assertEquals(artist1.getNote(), artist2.getNote());
        assertEquals(artist1.getSongs(), artist2.getSongs());
    }

    private void deepAssert(Object[] arr1, Object[] arr2) {
        assertEquals(arr1.length, arr2.length);

        for (int i = 0; i < arr1.length; i++) {
            Artist artist1 = (Artist) arr1[i];
            Artist artist2 = (Artist) arr2[i];
            deepAssert(artist1, artist2);
        }
    }
}