package project.pa165.musiclibrary.services;

import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import project.pa165.musiclibrary.dao.SongDao;
import project.pa165.musiclibrary.dto.SongDto;
import project.pa165.musiclibrary.entities.Genre;
import project.pa165.musiclibrary.entities.Song;
import project.pa165.musiclibrary.exception.PersistenceException;
import project.pa165.musiclibrary.exception.ServiceException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Alex
 */
public class SongManagerImplTest {

    private Song song1;
    private Song song2;
    private SongDto songDto1;
    private SongDto songDto2;

    @Mock
    private SongDao songDao;

    @InjectMocks
    private SongManagerImpl songManager;

    public SongManagerImpl getSongManager() {
        return songManager;
    }

    @Before
    public void setUp() throws PersistenceException {
        MockitoAnnotations.initMocks(this);

        songManager.setDozerBeanMapper(new DozerBeanMapper());

        song1 = createSong("Walk", (short) 1, 200, Genre.ROCK, 320, "test");
        song2 = createSong("Arlandria", (short) 2, 300, Genre.HOLIDAY, 128, "test");
        songDto1 = createSongDto("Walk", (short) 1, 200, Genre.ROCK, 320, "test");
        songDto2 = createSongDto("Arlandria", (short) 2, 300, Genre.HOLIDAY, 128, "test");

        when(songDao.find(1l)).thenReturn(song1);
        when(songDao.find(2l)).thenReturn(song2);
        when(songDao.find(3l)).thenReturn(null);

        when(songDao.getAll()).thenReturn(Arrays.asList(song1, song2));
    }


    @Test
    public void testCreateSong() throws PersistenceException, ServiceException {
        ArgumentCaptor<Song> argumentCaptor = ArgumentCaptor.forClass(Song.class);
        getSongManager().createSong(songDto1);
        verify(songDao).create(argumentCaptor.capture());
        deepAssert(song1, argumentCaptor.getValue());
    }

    @Test(expected = ServiceException.class)
    public void testCreateSongNull() throws PersistenceException, ServiceException {
        doThrow(new PersistenceException("song")).when(songDao).create(null);
        songManager.createSong(null);
        verify(songDao).create(null);
    }

    @Test
    public void testDeleteSong() throws PersistenceException, ServiceException {
        getSongManager().deleteSong(1l);
        verify(songDao).delete(1l);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteSongNull() throws PersistenceException, ServiceException {
        doThrow(new PersistenceException("id")).when(songDao).delete(null);
        songManager.deleteSong(null);
        verify(songDao).delete(null);
    }

    @Test
    public void testUpdateSong() throws PersistenceException, ServiceException {
        ArgumentCaptor<Song> argumentCaptor = ArgumentCaptor.forClass(Song.class);
        getSongManager().updateSong(songDto1);
        verify(songDao).update(argumentCaptor.capture());
        deepAssert(song1, argumentCaptor.getValue());
    }

    @Test(expected = ServiceException.class)
    public void testUpdateSongNull() throws PersistenceException, ServiceException {
        doThrow(new PersistenceException("song")).when(songDao).update(null);
        songManager.updateSong(null);
        verify(songDao).update(null);
    }

    @Test
    public void testFindSong() throws PersistenceException, ServiceException {
        SongDto result = getSongManager().findSong(1l);
        verify(songDao).find(1l);
        deepAssert(songDto1, result);
    }

    @Test
    public void testFindSongEmptyDb() throws PersistenceException, ServiceException {
        when(songDao.find(any(Long.class))).thenReturn(null);

        SongDto result = getSongManager().findSong(1l);
        verify(songDao).find(1l);
        assertNull(result);
    }

    @Test
    public void testFindSongWrongId() throws PersistenceException, ServiceException {
        SongDto result = getSongManager().findSong(3l);
        verify(songDao).find(3l);
        assertNull(result);
    }

    @Test(expected = ServiceException.class)
    public void testFindSongWithNullId() throws PersistenceException, ServiceException {
        doThrow(new PersistenceException("id")).when(songDao).find(null);
        getSongManager().findSong(null);
        verify(songDao).find(null);
    }

    @Test
    public void testGetAllSongs() throws PersistenceException, ServiceException {
        List<SongDto> allSongs = Arrays.asList(songDto1, songDto2);
        List<SongDto> result = getSongManager().getAllSongs();

        verify(songDao).getAll();

        assertEquals(2, result.size());
        deepAssert(allSongs.toArray(), result.toArray());
    }

    @Test
    public void testGetAllSongsEmptyDb() throws PersistenceException, ServiceException {
        when(songDao.getAll()).thenReturn(new ArrayList<Song>());

        List<SongDto> result = getSongManager().getAllSongs();

        verify(songDao).getAll();

        assertEquals(0, result.size());
    }

    @Test(expected = ServiceException.class)
    public void testGetAllSongsWithExceptionOnPersistence() throws PersistenceException, ServiceException {
        doThrow(new PersistenceException()).when(songDao).getAll();
        getSongManager().getAllSongs();
        verify(songDao).getAll();
    }

    @Test
    public void testFindSongByTitleWithUniqueTitle() throws PersistenceException, ServiceException {
        List<Song> allSongs = Arrays.asList(song1);
        when(songDao.findSongByTitle(any(String.class))).thenReturn(allSongs);

        List<SongDto> result = getSongManager().findSongByTitle("Walk");

        verify(songDao).findSongByTitle("Walk");

        assertEquals(1, result.size());
        deepAssert(Arrays.asList(songDto1).toArray(), result.toArray());
    }

    @Test
    public void testFindSongByTitleWithMultipleSameTitleSong() throws PersistenceException, ServiceException {
        List<Song> allSongs = Arrays.asList(song1, song2);
        when(songDao.findSongByTitle(any(String.class))).thenReturn(allSongs);

        List<SongDto> result = getSongManager().findSongByTitle("wal");

        verify(songDao).findSongByTitle("wal");

        assertEquals(2, result.size());
        deepAssert(Arrays.asList(songDto1, songDto2).toArray(), result.toArray());
    }

    @Test
    public void testFindSongByTitleEmptyDb() throws PersistenceException, ServiceException {
        when(songDao.findSongByTitle(any(String.class))).thenReturn(new ArrayList<Song>());

        List<SongDto> result = getSongManager().findSongByTitle("Walk");

        verify(songDao).findSongByTitle("Walk");

        assertEquals(0, result.size());
    }

    private SongDto createSongDto(String title, Short trackNumber, Integer length, Genre genre, Integer bitrate,
                                  String note) {
        SongDto song = new SongDto();
        song.setTitle(title);
        song.setTrackNumber(trackNumber);
        song.setLength(length);
        song.setGenre(genre);
        song.setBitrate(bitrate);
        song.setNote(note);
        return song;
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
        assertEquals(song1.getTrackNumber(), song2.getTrackNumber());
        assertEquals(song1.getLength(), song2.getLength());
        assertEquals(song1.getGenre(), song2.getGenre());
        assertEquals(song1.getBitrate(), song2.getBitrate());
        assertEquals(song1.getNote(), song2.getNote());
    }

    private void deepAssert(SongDto song1, SongDto song2) {
        assertEquals(song1.getId(), song2.getId());
        assertEquals(song1.getTitle(), song2.getTitle());
        assertEquals(song1.getTrackNumber(), song2.getTrackNumber());
        assertEquals(song1.getLength(), song2.getLength());
        assertEquals(song1.getGenre(), song2.getGenre());
        assertEquals(song1.getBitrate(), song2.getBitrate());
        assertEquals(song1.getNote(), song2.getNote());
    }

    private void deepAssert(Object[] arr1, Object[] arr2) {
        assertEquals(arr1.length, arr2.length);

        for (int i = 0; i < arr1.length; i++) {
            SongDto song1 = (SongDto) arr1[i];
            SongDto song2 = (SongDto) arr2[i];
            deepAssert(song1, song2);
        }
    }

}
