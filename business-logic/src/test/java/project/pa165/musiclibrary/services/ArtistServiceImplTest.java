package project.pa165.musiclibrary.services;

import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import project.pa165.musiclibrary.dao.ArtistDao;
import project.pa165.musiclibrary.dto.ArtistDto;
import project.pa165.musiclibrary.entities.Artist;
import project.pa165.musiclibrary.exception.ServiceException;
import project.pa165.musiclibrary.exception.ServiceException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

/**
 * @author Matúš
 */
@RunWith(MockitoJUnitRunner.class)
public class ArtistServiceImplTest {

    private Artist artist1;
    private Artist artist2;
    private ArtistDto artistDto1;
    private ArtistDto artistDto2;

    @Mock
    private ArtistDao artistDao;

    @InjectMocks
    private ArtistServiceImpl artistService;

    public ArtistServiceImpl getArtistService() {
        return artistService;
    }

    @Before
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);

        artistService.setDozerBeanMapper(new DozerBeanMapper());

        artist1 = createArtist(1l, "Alfa", "Testing artist1");
        artist2 = createArtist(2l, "Beta", "Testing artist2");
        artistDto1 = createArtistDto(1l, "Alfa", "Testing artist1");
        artistDto2 = createArtistDto(2l, "Beta", "Testing artist2");

        when(artistDao.find(1l)).thenReturn(artist1);
        when(artistDao.find(2l)).thenReturn(artist2);
        when(artistDao.find(3l)).thenReturn(null);

        when(artistDao.getAll()).thenReturn(Arrays.asList(artist1, artist2));
    }

    @Test
    public void testCreateArtist() {
        ArgumentCaptor<Artist> argumentCaptor = ArgumentCaptor.forClass(Artist.class);
        getArtistService().createArtist(artistDto1);
        verify(artistDao).create(argumentCaptor.capture());
        deepAssert(artist1, argumentCaptor.getValue());
    }

    @Test(expected = ServiceException.class)
    public void testCreateArtistNull() {
        doThrow(new ServiceException("artist")).when(artistDao).create(null);
        artistService.createArtist(null);
        verify(artistDao).create(null);
    }

    @Test
    public void testDeleteArtist() {
        getArtistService().deleteArtist(1l);
        verify(artistDao).delete(1l);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteArtistNull() {
        doThrow(new ServiceException("id")).when(artistDao).delete(null);
        artistService.deleteArtist(null);
        verify(artistDao).delete(null);
    }

    @Test
    public void testUpdateArtist() {
        ArgumentCaptor<Artist> argumentCaptor = ArgumentCaptor.forClass(Artist.class);
        getArtistService().updateArtist(artistDto1);
        verify(artistDao).update(argumentCaptor.capture());
        deepAssert(artist1, argumentCaptor.getValue());
    }

    @Test(expected = ServiceException.class)
    public void testUpdateArtistNull() {
        doThrow(new ServiceException("album")).when(artistDao).update(null);
        artistService.updateArtist(null);
        verify(artistDao).update(null);
    }

    @Test
    public void testFindArtist() {
        ArtistDto result = getArtistService().findArtist(1l);
        verify(artistDao).find(1l);
        deepAssert(artistDto1, result);
    }

    @Test
    public void testFindArtistEmptyDb() {
        when(artistDao.find(any(Long.class))).thenReturn(null);

        ArtistDto result = getArtistService().findArtist(1l);
        verify(artistDao).find(1l);
        assertNull(result);
    }

    @Test
    public void testFindArtistWrongId() {
        ArtistDto result = getArtistService().findArtist(3l);
        verify(artistDao).find(3l);
        assertNull(result);
    }

    @Test(expected = ServiceException.class)
    public void testFindArtistWithNullId() {
        doThrow(new ServiceException("id")).when(artistDao).find(null);
        getArtistService().findArtist(null);
        verify(artistDao).find(null);
    }

    @Test
    public void testGetAllArtists() {
        List<ArtistDto> allArtists = Arrays.asList(artistDto1, artistDto2);
        List<ArtistDto> result = getArtistService().getAllArtists();

        verify(artistDao).getAll();

        assertEquals(2, result.size());
        deepAssert(allArtists.toArray(), result.toArray());
    }

    @Test
    public void testGetAllArtistsEmptyDb() {
        when(artistDao.getAll()).thenReturn(new ArrayList<Artist>());

        List<ArtistDto> result = getArtistService().getAllArtists();

        verify(artistDao).getAll();

        assertEquals(0, result.size());
    }

    @Test(expected = ServiceException.class)
    public void testGetAllArtistsWithExceptionOnPersistence() {
        doThrow(new ServiceException()).when(artistDao).getAll();
        getArtistService().getAllArtists();
        verify(artistDao).getAll();
    }

    @Test
    public void testFindArtistByNameWithUniqueTitle() {
        List<Artist> allArtists = Arrays.asList(artist1);
        when(artistDao.findArtistByName(any(String.class))).thenReturn(allArtists);

        List<ArtistDto> result = getArtistService().findArtistByName("Unity");

        verify(artistDao).findArtistByName("Unity");

        assertEquals(1, result.size());
        deepAssert(Arrays.asList(artistDto1).toArray(), result.toArray());
    }

    @Test
    public void testFindArtistByNameWithMultipleSameTitleAlbum() {
        List<Artist> allArtists = Arrays.asList(artist1, artist2);
        when(artistDao.findArtistByName(any(String.class))).thenReturn(allArtists);

        List<ArtistDto> result = getArtistService().findArtistByName("uni");

        verify(artistDao).findArtistByName("uni");

        assertEquals(2, result.size());
        deepAssert(Arrays.asList(artistDto1, artistDto2).toArray(), result.toArray());
    }

    @Test
    public void testFindArtistByNameEmptyDb() {
        when(artistDao.findArtistByName(any(String.class))).thenReturn(new ArrayList<Artist>());

        List<ArtistDto> result = getArtistService().findArtistByName("Unity");

        verify(artistDao).findArtistByName("Unity");

        assertEquals(0, result.size());
    }

    private ArtistDto createArtistDto(Long id, String name, String note) {
        ArtistDto artist = new ArtistDto();
        artist.setId(id);
        artist.setAlias(name);
        artist.setNote(note);
        return artist;
    }

    private Artist createArtist(Long id, String name, String note) {
        Artist artist = new Artist();
        artist.setId(id);
        artist.setAlias(name);
        artist.setNote(note);
        return artist;
    }

    private void deepAssert(Artist artist1, Artist artist2) {
        assertEquals(artist1.getId(), artist2.getId());
        assertEquals(artist1.getAlias(), artist2.getAlias());
        assertEquals(artist1.getNote(), artist2.getNote());
    }

    private void deepAssert(ArtistDto artist1, ArtistDto artist2) {
        assertEquals(artist1.getId(), artist2.getId());
        assertEquals(artist1.getAlias(), artist2.getAlias());
        assertEquals(artist1.getNote(), artist2.getNote());
    }

    private void deepAssert(Object[] arr1, Object[] arr2) {
        assertEquals(arr1.length, arr2.length);

        for (int i = 0; i < arr1.length; i++) {
            ArtistDto artist1 = (ArtistDto) arr1[i];
            ArtistDto artist2 = (ArtistDto) arr2[i];
            deepAssert(artist1, artist2);
        }
    }
}
