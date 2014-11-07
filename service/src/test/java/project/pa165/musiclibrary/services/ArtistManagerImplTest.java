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
import project.pa165.musiclibrary.exception.PersistenceException;
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
public class ArtistManagerImplTest {

    private Artist artist1;
    private Artist artist2;
    private ArtistDto artistDto1;
    private ArtistDto artistDto2;

    @Mock
    private ArtistDao artistDao;

    @InjectMocks
    private ArtistManagerImpl artistManager;

    public ArtistManagerImpl getArtistManager() {
        return artistManager;
    }

    @Before
    public void setup() throws PersistenceException {
        MockitoAnnotations.initMocks(this);

        artistManager.setDozerBeanMapper(new DozerBeanMapper());

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
    public void testCreateArtist() throws PersistenceException, ServiceException {
        ArgumentCaptor<Artist> argumentCaptor = ArgumentCaptor.forClass(Artist.class);
        getArtistManager().createArtist(artistDto1);
        verify(artistDao).create(argumentCaptor.capture());
        deepAssert(artist1, argumentCaptor.getValue());
    }

    @Test(expected = ServiceException.class)
    public void testCreateArtistNull() throws PersistenceException, ServiceException {
        doThrow(new PersistenceException("artist")).when(artistDao).create(null);
        artistManager.createArtist(null);
        verify(artistDao).create(null);
    }

    @Test
    public void testDeleteArtist() throws PersistenceException, ServiceException {
        getArtistManager().deleteArtist(1l);
        verify(artistDao).delete(1l);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteArtistNull() throws PersistenceException, ServiceException {
        doThrow(new PersistenceException("id")).when(artistDao).delete(null);
        artistManager.deleteArtist(null);
        verify(artistDao).delete(null);
    }

    @Test
    public void testUpdateArtist() throws PersistenceException, ServiceException {
        ArgumentCaptor<Artist> argumentCaptor = ArgumentCaptor.forClass(Artist.class);
        getArtistManager().updateArtist(artistDto1);
        verify(artistDao).update(argumentCaptor.capture());
        deepAssert(artist1, argumentCaptor.getValue());
    }

    @Test(expected = ServiceException.class)
    public void testUpdateArtistNull() throws PersistenceException, ServiceException {
        doThrow(new PersistenceException("album")).when(artistDao).update(null);
        artistManager.updateArtist(null);
        verify(artistDao).update(null);
    }

    @Test
    public void testFindArtist() throws PersistenceException, ServiceException {
        ArtistDto result = getArtistManager().findArtist(1l);
        verify(artistDao).find(1l);
        deepAssert(artistDto1, result);
    }

    @Test
    public void testFindArtistEmptyDb() throws PersistenceException, ServiceException {
        when(artistDao.find(any(Long.class))).thenReturn(null);

        ArtistDto result = getArtistManager().findArtist(1l);
        verify(artistDao).find(1l);
        assertNull(result);
    }

    @Test
    public void testFindArtistWrongId() throws PersistenceException, ServiceException {
        ArtistDto result = getArtistManager().findArtist(3l);
        verify(artistDao).find(3l);
        assertNull(result);
    }

    @Test(expected = ServiceException.class)
    public void testFindArtistWithNullId() throws PersistenceException, ServiceException {
        doThrow(new PersistenceException("id")).when(artistDao).find(null);
        getArtistManager().findArtist(null);
        verify(artistDao).find(null);
    }

    @Test
    public void testGetAllArtists() throws PersistenceException, ServiceException {
        List<ArtistDto> allArtists = Arrays.asList(artistDto1, artistDto2);
        List<ArtistDto> result = getArtistManager().getAllArtists();

        verify(artistDao).getAll();

        assertEquals(2, result.size());
        deepAssert(allArtists.toArray(), result.toArray());
    }

    @Test
    public void testGetAllArtistsEmptyDb() throws PersistenceException, ServiceException {
        when(artistDao.getAll()).thenReturn(new ArrayList<Artist>());

        List<ArtistDto> result = getArtistManager().getAllArtists();

        verify(artistDao).getAll();

        assertEquals(0, result.size());
    }

    @Test(expected = ServiceException.class)
    public void testGetAllArtistsWithExceptionOnPersistence() throws PersistenceException, ServiceException {
        doThrow(new PersistenceException()).when(artistDao).getAll();
        getArtistManager().getAllArtists();
        verify(artistDao).getAll();
    }

    @Test
    public void testFindArtistByNameWithUniqueTitle() throws PersistenceException, ServiceException {
        List<Artist> allArtists = Arrays.asList(artist1);
        when(artistDao.findArtistByName(any(String.class))).thenReturn(allArtists);

        List<ArtistDto> result = getArtistManager().findArtistByName("Unity");

        verify(artistDao).findArtistByName("Unity");

        assertEquals(1, result.size());
        deepAssert(Arrays.asList(artistDto1).toArray(), result.toArray());
    }

    @Test
    public void testFindArtistByNameWithMultipleSameTitleAlbum() throws PersistenceException, ServiceException {
        List<Artist> allArtists = Arrays.asList(artist1, artist2);
        when(artistDao.findArtistByName(any(String.class))).thenReturn(allArtists);

        List<ArtistDto> result = getArtistManager().findArtistByName("uni");

        verify(artistDao).findArtistByName("uni");

        assertEquals(2, result.size());
        deepAssert(Arrays.asList(artistDto1, artistDto2).toArray(), result.toArray());
    }

    @Test
    public void testFindArtistByNameEmptyDb() throws PersistenceException, ServiceException {
        when(artistDao.findArtistByName(any(String.class))).thenReturn(new ArrayList<Artist>());

        List<ArtistDto> result = getArtistManager().findArtistByName("Unity");

        verify(artistDao).findArtistByName("Unity");

        assertEquals(0, result.size());
    }

    private ArtistDto createArtistDto(Long id, String name, String note) {
        ArtistDto artist = new ArtistDto();
        artist.setId(id);
        artist.setName(name);
        artist.setNote(note);
        return artist;
    }

    private Artist createArtist(Long id, String name, String note) {
        Artist artist = new Artist();
        artist.setId(id);
        artist.setName(name);
        artist.setNote(note);
        return artist;
    }

    private void deepAssert(Artist artist1, Artist artist2) {
        assertEquals(artist1.getId(), artist2.getId());
        assertEquals(artist1.getName(), artist2.getName());
        assertEquals(artist1.getNote(), artist2.getNote());
    }

    private void deepAssert(ArtistDto artist1, ArtistDto artist2) {
        assertEquals(artist1.getId(), artist2.getId());
        assertEquals(artist1.getName(), artist2.getName());
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
