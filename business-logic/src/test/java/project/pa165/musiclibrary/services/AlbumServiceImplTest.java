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
import project.pa165.musiclibrary.aspect.ExceptionTranslationAspect;
import project.pa165.musiclibrary.dao.AlbumDao;
import project.pa165.musiclibrary.dto.AlbumDto;
import project.pa165.musiclibrary.entities.Album;
import project.pa165.musiclibrary.exception.ServiceException;
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
    private AlbumServiceImpl albumService;

    public AlbumServiceImpl getAlbumService() {
        return albumService;
    }

    @Before
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);

        List<String> myMappingFiles = new ArrayList<>();
        myMappingFiles.add("dozer-mapping.xml");
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(myMappingFiles);

        albumService.setDozerBeanMapper(mapper);

        album1 = createAlbum(1l, "Unity", new LocalDate(1991, 2, 6).toDate(), "http://pathtocoverart.com", "album");
        album2 = createAlbum(2l, "Hello Uni", new LocalDate(2001, 1, 1).toDate(), "http://blabla.com", "note2");
        albumDto1 = createAlbumDto(1l, "Unity", "06-02-1991", "http://pathtocoverart.com", "album");
        albumDto2 = createAlbumDto(2l, "Hello Uni", "01-01-2001", "http://blabla.com", "note2");

        when(albumDao.find(1l)).thenReturn(album1);
        when(albumDao.find(2l)).thenReturn(album2);
        when(albumDao.find(3l)).thenReturn(null);

        when(albumDao.getAll()).thenReturn(Arrays.asList(album1, album2));
    }

    @Test
    public void testCreateAlbum() {
        ArgumentCaptor<Album> argumentCaptor = ArgumentCaptor.forClass(Album.class);
        getAlbumService().createAlbum(albumDto1);
        verify(albumDao).create(argumentCaptor.capture());
        deepAssert(album1, argumentCaptor.getValue());
    }

    @Test(expected = ServiceException.class)
    public void testCreateAlbumNull() {
        doThrow(new ServiceException("album")).when(albumDao).create(null);
        albumService.createAlbum(null);
        verify(albumDao).create(null);
    }

    @Test
    public void testDeleteAlbum() {
        getAlbumService().deleteAlbum(1l);
        verify(albumDao).delete(1l);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteAlbumNull() {
        doThrow(new ServiceException("id")).when(albumDao).delete(null);
        albumService.deleteAlbum(null);
        verify(albumDao).delete(null);
    }

    @Test
    public void testUpdateAlbum() {
        ArgumentCaptor<Album> argumentCaptor = ArgumentCaptor.forClass(Album.class);
        getAlbumService().updateAlbum(albumDto1);
        verify(albumDao).update(argumentCaptor.capture());
        deepAssert(album1, argumentCaptor.getValue());
    }

    @Test(expected = ServiceException.class)
    public void testUpdateAlbumNull() {
        doThrow(new ServiceException("album")).when(albumDao).update(null);
        albumService.updateAlbum(null);
        verify(albumDao).update(null);
    }

    @Test
    public void testFindAlbum() {
        AlbumDto result = getAlbumService().findAlbum(1l);
        verify(albumDao).find(1l);
        deepAssert(albumDto1, result);
    }

    @Test
    public void testFindAlbumEmptyDb() {
        when(albumDao.find(any(Long.class))).thenReturn(null);

        AlbumDto result = getAlbumService().findAlbum(1l);
        verify(albumDao).find(1l);
        assertNull(result);
    }

    @Test
    public void testFindAlbumWrongId() {
        AlbumDto result = getAlbumService().findAlbum(3l);
        verify(albumDao).find(3l);
        assertNull(result);
    }

    @Test(expected = ServiceException.class)
    public void testFindAlbumWithNullId() {
        doThrow(new ServiceException("id")).when(albumDao).find(null);
        getAlbumService().findAlbum(null);
        verify(albumDao).find(null);
    }

    @Test
    public void testGetAllAlbums() {
        List<AlbumDto> allAlbums = Arrays.asList(albumDto1, albumDto2);
        List<AlbumDto> result = getAlbumService().getAllAlbums();

        verify(albumDao).getAll();

        assertEquals(2, result.size());
        deepAssert(allAlbums.toArray(), result.toArray());
    }

    @Test
    public void testGetAllAlbumsEmptyDb() {
        when(albumDao.getAll()).thenReturn(new ArrayList<Album>());

        List<AlbumDto> result = getAlbumService().getAllAlbums();

        verify(albumDao).getAll();

        assertEquals(0, result.size());
    }

    @Test(expected = ServiceException.class)
    public void testGetAllAlbumsWithExceptionOnPersistence() {
        doThrow(new ServiceException()).when(albumDao).getAll();
        getAlbumService().getAllAlbums();
        verify(albumDao).getAll();
    }

    @Test
    public void testFindAlbumByTitleWithUniqueTitle() {
        List<Album> allAlbums = Arrays.asList(album1);
        when(albumDao.findAlbumByTitle(any(String.class))).thenReturn(allAlbums);

        List<AlbumDto> result = getAlbumService().findAlbumByTitle("Unity");

        verify(albumDao).findAlbumByTitle("Unity");

        assertEquals(1, result.size());
        deepAssert(Arrays.asList(albumDto1).toArray(), result.toArray());
    }

    @Test
    public void testFindAlbumByTitleWithMultipleSameTitleAlbum() {
        List<Album> allAlbums = Arrays.asList(album1, album2);
        when(albumDao.findAlbumByTitle(any(String.class))).thenReturn(allAlbums);

        List<AlbumDto> result = getAlbumService().findAlbumByTitle("uni");

        verify(albumDao).findAlbumByTitle("uni");

        assertEquals(2, result.size());
        deepAssert(Arrays.asList(albumDto1, albumDto2).toArray(), result.toArray());
    }

    @Test
    public void testFindAlbumByTitleEmptyDb() {
        when(albumDao.findAlbumByTitle(any(String.class))).thenReturn(new ArrayList<Album>());

        List<AlbumDto> result = getAlbumService().findAlbumByTitle("Unity");

        verify(albumDao).findAlbumByTitle("Unity");

        assertEquals(0, result.size());
    }

    private AlbumDto createAlbumDto(Long id, String title, String releaseDate, String coverArt, String note) {
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
