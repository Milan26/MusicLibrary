package project.pa165.musiclibrary.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import project.pa165.musiclibrary.entities.Genre;
import project.pa165.musiclibrary.entities.Song;
import project.pa165.musiclibrary.exception.DaoException;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Matus
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext-dao.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class SongDaoImplTest {

    private Song song1;
    private Song song2;

    private SongDao songDao;

    public SongDao getSongDao() {
        return songDao;
    }

    @Inject
    public void setSongDao(SongDao songDao) {
        this.songDao = songDao;
    }

    @Before
    public void setUp() {
        song1 = createSong("Walk", (short) 1, 200, Genre.ROCK, 320, "test");
        song2 = createSong("Arlandria", (short) 2, 300, Genre.HOLIDAY, 128, "test");
    }

    @Test
    public void testCreateSong() throws DaoException {
        persist(song1);
    }

    public void testCreatedSongProperties() throws DaoException {
        persist(song1);

        Song actual = getSongDao().find(song1.getId());
        deepAssert(song1, actual);
    }

    @Test(expected = DataAccessException.class)
    public void testCreateSongNull() throws DaoException {
        getSongDao().create(null);
    }

    @Test
    public void testDeleteSong() throws DaoException {
        persist(song1);

        getSongDao().delete(song1.getId());
        assertNull(getSongDao().find(song1.getId()));
    }

    @Test(expected = DataAccessException.class)
    public void testDeleteSongNull() throws DaoException {
        getSongDao().delete(null);
    }

    @Test
    public void testUpdateSong() throws DaoException {
        persist(song1);

        Song actual = getSongDao().find(song1.getId());
        assertNotEquals(Genre.ALTERNATIVE, actual.getGenre());
        assertNotEquals(Integer.valueOf(198), actual.getBitrate());
        assertNotEquals("Updated note", actual.getNote());

        song1.setGenre(Genre.ALTERNATIVE);
        song1.setBitrate(198);
        song1.setNote("Updated note");

        getSongDao().update(song1);
        actual = getSongDao().find(song1.getId());

        deepAssert(song1, actual);
    }

    @Test(expected = DataAccessException.class)
    public void testUpdateSongNull() throws DaoException {
        getSongDao().update(null);
    }

    @Test
    public void testFindSong() throws DaoException {
        persist(song1);

        Song actual = getSongDao().find(song1.getId());
        assertNotNull(actual);
        assertEquals(song1, actual);
    }

    @Test(expected = DataAccessException.class)
    public void testFindAlbumWithNullId() throws DaoException {
        getSongDao().find(song1.getId());
    }

    @Test
    public void testFindAlbumWithWrongId() throws DaoException {
        assertNull(getSongDao().find(5l));
    }

    @Test
    public void testGetAllSongs() throws DaoException {
        persist(song1);
        persist(song2);

        List<Song> expected = Arrays.asList(song1, song2);
        assertEquals(expected.size(), 2);
        List<Song> actual = getSongDao().getAll();
        assertEquals(actual.size(), 2);

        assertArrayEquals(expected.toArray(), actual.toArray());
        deepAssert(expected.toArray(), actual.toArray());
    }

    @Test
    public void testGetAllAlbumsEmptyDb() throws DaoException {
        List<Song> actual = getSongDao().getAll();
        assertEquals(actual.size(), 0);
    }

    @Test
    public void testFindSongByTitleWithUniqueTitle() throws DaoException {
        persist(song1);
        persist(song2);

        song1.setTitle("I am Unique");
        song2.setTitle("I am not");
        getSongDao().update(song1);
        getSongDao().update(song2);

        assertEquals(getSongDao().getAll().size(), 2);

        List<Song> expected = Arrays.asList(song1);
        List<Song> actual = getSongDao().findSongByTitle("unique");

        assertArrayEquals(expected.toArray(), actual.toArray());
        deepAssert(expected.toArray(), actual.toArray());
    }

    @Test
    public void testFindAlbumByTitleWithMultipleSameTitleAlbum() throws DaoException {
        persist(song1);
        persist(song2);

        song1.setTitle("I am Unique");
        song2.setTitle("I am not so UniQuE, or maybe I am");
        getSongDao().update(song1);
        getSongDao().update(song2);

        assertEquals(getSongDao().getAll().size(), 2);

        List<Song> expected = Arrays.asList(song1, song2);
        List<Song> actual = getSongDao().findSongByTitle("unique");

        assertArrayEquals(expected.toArray(), actual.toArray());
        deepAssert(expected.toArray(), actual.toArray());
    }

    @Test
    public void testFindAlbumByTitleWithAnyMatch() throws DaoException {
        persist(song1);
        persist(song2);

        assertEquals(getSongDao().getAll().size(), 2);

        List<Song> expected = new ArrayList<>();
        List<Song> actual = getSongDao().findSongByTitle("alhamra");

        assertArrayEquals(expected.toArray(), actual.toArray());
        deepAssert(expected.toArray(), actual.toArray());
    }

    @Test
    public void testFindAlbumByTitleOnEmptyDb() throws DaoException {
        List<Song> actual = getSongDao().getAll();
        assertEquals(actual.size(), 0);

        assertEquals(getSongDao().findSongByTitle("Unity").size(), 0);
    }

    private void persist(Song song) throws DaoException {
        assertNull(song.getId());
        getSongDao().create(song);
        assertNotNull(song.getId());
    }

    private Song createSong(String title, Short trackNumber, Integer length, Genre genre, Integer bitrate,
                            String note) {
        Song song = new Song();
        song.setTitle(title);
        song.setTrackNumber(trackNumber);
        song.setLength(length);
        song.setGenre(genre);
        song.setBitrate(bitrate);
        song.setNote(note);
        return song;
    }

    private void deepAssert(Song song1, Song song2) {
        assertEquals(song1.getId(), song2.getId());
        assertEquals(song1.getTitle(), song2.getTitle());
        assertEquals(song1.getBitrate(), song2.getBitrate());
        assertEquals(song1.getGenre(), song2.getGenre());
        assertEquals(song1.getLength(), song2.getLength());
        assertEquals(song1.getNote(), song2.getNote());
        assertEquals(song1.getTrackNumber(), song2.getTrackNumber());
    }

    private void deepAssert(Object[] arr1, Object[] arr2) {
        assertEquals(arr1.length, arr2.length);

        for (int i = 0; i < arr1.length; i++) {
            Song song1 = (Song) arr1[i];
            Song song2 = (Song) arr2[i];
            deepAssert(song1, song2);
        }
    }
}