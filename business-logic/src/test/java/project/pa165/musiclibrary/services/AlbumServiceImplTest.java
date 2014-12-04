package project.pa165.musiclibrary.services;

import org.dozer.DozerBeanMapper;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import project.pa165.musiclibrary.dao.AlbumDao;
import project.pa165.musiclibrary.dto.AlbumDto;
import project.pa165.musiclibrary.entities.Album;
import project.pa165.musiclibrary.exception.PersistenceException;
import project.pa165.musiclibrary.exception.ServiceException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

/**
 * @author Milan
 */
@RunWith(MockitoJUnitRunner.class)
public class AlbumServiceImplTest {

    private Album album1;
    private Album album2;
    private AlbumDto albumDto1;
    private AlbumDto albumDto2;

    @Mock
    private AlbumDao albumDao;

    @InjectMocks
    private AlbumServiceImpl albumManager;

    public AlbumServiceImpl getAlbumManager() {
        return albumManager;
    }

    @Before
    public void setup() throws PersistenceException {
        MockitoAnnotations.initMocks(this);

        albumManager.setDozerBeanMapper(new DozerBeanMapper());

        album1 = createAlbum(1l, "Unity", new LocalDate(1991, 2, 6).toDate(), "http://pathtocoverart.com", "album");
        album2 = createAlbum(2l, "Hello Uni", new LocalDate(2001, 1, 1).toDate(), "http://blabla.com", "I am hungry");
        albumDto1 = createAlbumDto(1l, "Unity", new LocalDate(1991, 2, 6).toDate(), "http://pathtocoverart.com",
                "album");
        albumDto2 = createAlbumDto(2l, "Hello Uni", new LocalDate(2001, 1, 1).toDate(), "http://blabla.com", "I am "
                + "hungry");

        when(albumDao.find(1l)).thenReturn(album1);
        when(albumDao.find(2l)).thenReturn(album2);
        when(albumDao.find(3l)).thenReturn(null);

        when(albumDao.getAll()).thenReturn(Arrays.asList(album1, album2));
    }

    @Test
    public void testCreateAlbum() throws PersistenceException, ServiceException {
        ArgumentCaptor<Album> argumentCaptor = ArgumentCaptor.forClass(Album.class);
        getAlbumManager().createAlbum(albumDto1);
        verify(albumDao).create(argumentCaptor.capture());
        deepAssert(album1, argumentCaptor.getValue());
    }

    @Test(expected = ServiceException.class)
    public void testCreateAlbumNull() throws PersistenceException, ServiceException {
        doThrow(new PersistenceException("album")).when(albumDao).create(null);
        albumManager.createAlbum(null);
        verify(albumDao).create(null);
    }

    @Test
    public void testDeleteAlbum() throws PersistenceException, ServiceException {
        getAlbumManager().deleteAlbum(1l);
        verify(albumDao).delete(1l);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteAlbumNull() throws PersistenceException, ServiceException {
        doThrow(new PersistenceException("id")).when(albumDao).delete(null);
        albumManager.deleteAlbum(null);
        verify(albumDao).delete(null);
    }

    @Test
    public void testUpdateAlbum() throws PersistenceException, ServiceException {
        ArgumentCaptor<Album> argumentCaptor = ArgumentCaptor.forClass(Album.class);
        getAlbumManager().updateAlbum(albumDto1);
        verify(albumDao).update(argumentCaptor.capture());
        deepAssert(album1, argumentCaptor.getValue());
    }

    @Test(expected = ServiceException.class)
    public void testUpdateAlbumNull() throws PersistenceException, ServiceException {
        doThrow(new PersistenceException("album")).when(albumDao).update(null);
        albumManager.updateAlbum(null);
        verify(albumDao).update(null);
    }

    @Test
    public void testFindAlbum() throws PersistenceException, ServiceException {
        AlbumDto result = getAlbumManager().findAlbum(1l);
        verify(albumDao).find(1l);
        deepAssert(albumDto1, result);
    }

    @Test
    public void testFindAlbumEmptyDb() throws PersistenceException, ServiceException {
        when(albumDao.find(any(Long.class))).thenReturn(null);

        AlbumDto result = getAlbumManager().findAlbum(1l);
        verify(albumDao).find(1l);
        assertNull(result);
    }

    @Test
    public void testFindAlbumWrongId() throws PersistenceException, ServiceException {
        AlbumDto result = getAlbumManager().findAlbum(3l);
        verify(albumDao).find(3l);
        assertNull(result);
    }

    @Test(expected = ServiceException.class)
    public void testFindAlbumWithNullId() throws PersistenceException, ServiceException {
        doThrow(new PersistenceException("id")).when(albumDao).find(null);
        getAlbumManager().findAlbum(null);
        verify(albumDao).find(null);
    }

    @Test
    public void testGetAllAlbums() throws PersistenceException, ServiceException {
        List<AlbumDto> allAlbums = Arrays.asList(albumDto1, albumDto2);
        List<AlbumDto> result = getAlbumManager().getAllAlbums();

        verify(albumDao).getAll();

        assertEquals(2, result.size());
        deepAssert(allAlbums.toArray(), result.toArray());
    }

    @Test
    public void testGetAllAlbumsEmptyDb() throws PersistenceException, ServiceException {
        when(albumDao.getAll()).thenReturn(new ArrayList<Album>());

        List<AlbumDto> result = getAlbumManager().getAllAlbums();

        verify(albumDao).getAll();

        assertEquals(0, result.size());
    }

    @Test(expected = ServiceException.class)
    public void testGetAllAlbumsWithExceptionOnPersistence() throws PersistenceException, ServiceException {
        doThrow(new PersistenceException()).when(albumDao).getAll();
        getAlbumManager().getAllAlbums();
        verify(albumDao).getAll();
    }

    @Test
    public void testFindAlbumByTitleWithUniqueTitle() throws PersistenceException, ServiceException {
        List<Album> allAlbums = Arrays.asList(album1);
        when(albumDao.findAlbumByTitle(any(String.class))).thenReturn(allAlbums);

        List<AlbumDto> result = getAlbumManager().findAlbumByTitle("Unity");

        verify(albumDao).findAlbumByTitle("Unity");

        assertEquals(1, result.size());
        deepAssert(Arrays.asList(albumDto1).toArray(), result.toArray());
    }

    @Test
    public void testFindAlbumByTitleWithMultipleSameTitleAlbum() throws PersistenceException, ServiceException {
        List<Album> allAlbums = Arrays.asList(album1, album2);
        when(albumDao.findAlbumByTitle(any(String.class))).thenReturn(allAlbums);

        List<AlbumDto> result = getAlbumManager().findAlbumByTitle("uni");

        verify(albumDao).findAlbumByTitle("uni");

        assertEquals(2, result.size());
        deepAssert(Arrays.asList(albumDto1, albumDto2).toArray(), result.toArray());
    }

    @Test
    public void testFindAlbumByTitleEmptyDb() throws PersistenceException, ServiceException {
        when(albumDao.findAlbumByTitle(any(String.class))).thenReturn(new ArrayList<Album>());

        List<AlbumDto> result = getAlbumManager().findAlbumByTitle("Unity");

        verify(albumDao).findAlbumByTitle("Unity");

        assertEquals(0, result.size());
    }

    private AlbumDto createAlbumDto(Long id, String title, Date releaseDate, String coverArt, String note) {
        AlbumDto album = new AlbumDto();
        album.setId(id);
        album.setTitle(title);
        album.setReleaseDate(releaseDate);
        album.setCoverArt(coverArt);
        album.setNote(note);
        return album;
    }

    private Album createAlbum(Long id, String title, Date releaseDate, String coverArt, String note) {
        Album album = new Album();
        album.setId(id);
        album.setTitle(title);
        album.setReleaseDate(releaseDate);
        album.setCoverArt(coverArt);
        album.setNote(note);
        return album;
    }

    private void deepAssert(Album album1, Album album2) {
        assertEquals(album1.getId(), album2.getId());
        assertEquals(album1.getTitle(), album2.getTitle());
        assertEquals(album1.getReleaseDate(), album2.getReleaseDate());
        assertEquals(album1.getCoverArt(), album2.getCoverArt());
        assertEquals(album1.getNote(), album2.getNote());
    }

    private void deepAssert(AlbumDto album1, AlbumDto album2) {
        assertEquals(album1.getId(), album2.getId());
        assertEquals(album1.getTitle(), album2.getTitle());
        assertEquals(album1.getReleaseDate(), album2.getReleaseDate());
        assertEquals(album1.getCoverArt(), album2.getCoverArt());
        assertEquals(album1.getNote(), album2.getNote());
    }

    private void deepAssert(Object[] arr1, Object[] arr2) {
        assertEquals(arr1.length, arr2.length);

        for (int i = 0; i < arr1.length; i++) {
            AlbumDto album1 = (AlbumDto) arr1[i];
            AlbumDto album2 = (AlbumDto) arr2[i];
            deepAssert(album1, album2);
        }
    }

}
