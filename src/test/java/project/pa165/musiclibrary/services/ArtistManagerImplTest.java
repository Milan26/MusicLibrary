package project.pa165.musiclibrary.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import project.pa165.musiclibrary.entities.Artist;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Alex
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ArtistManagerImplTest {

    @Inject
    private ArtistManager artistManager;

    /**
     * Test of createArtist method, of class ArtistServiceImpl.
     */
    @Test
    public void testCreateArtist() {

        Artist artist = new Artist();
        artist.setName("The Testers");

        assertNull(artist.getId());
        artistManager.createArtist(artist);
        assertNotNull(artist.getId());
    }

    /**
     *
     */
    @Test
    public void testCreatedArtistProperties() {

        Artist artist = new Artist();
        artist.setName("The Testers");
        artist.setNote("Testing artist The Testers");

        assertNull(artist.getId());
        artistManager.createArtist(artist);
        Long id = artist.getId();
        assertNotNull(id);

        Artist test = artistManager.findArtist(id);
        assertEquals(artist.getName(), test.getName());
        assertEquals(artist.getNote(), test.getNote());
        assertNull(artist.getSongs());
    }

    /**
     * Test of deleteArtist method, of class ArtistServiceImpl.
     */
    @Test
    public void testDeleteArtist() {
        Artist artist = new Artist();
        artist.setName("The Testers");

        assertNull(artist.getId());
        artistManager.createArtist(artist);

        artistManager.deleteArtist(artist.getId());
        assertNull(artistManager.findArtist(artist.getId()));
    }

    /**
     * Test of updateAlbum method, of class ArtistServiceImpl.
     */
    @Test
    public void testUpdateArtist() {
        Artist artist = new Artist();
        artist.setName("The Testers");

        assertNull(artist.getId());
        artistManager.createArtist(artist);
        assertNotNull(artist.getId());

        Long id = artist.getId();
        Artist test = artistManager.findArtist(id);
        assertNull(test.getNote());

        artist.setNote("Testing artist The Testers");

        artistManager.updateArtist(artist);
        test = artistManager.findArtist(id);

        assertNotNull(test.getNote());

        assertEquals(artist, test);
    }

    /**
     * Test of findArtist method, on empty database.
     */
    @Test
    public void testFindArtistOnEmptyDb() {
        assertNull(artistManager.findArtist(1l));
    }

    /**
     * Test of findArtist method, of class ArtistServiceImpl.
     */
    @Test
    public void testFindArtist() {
        Artist artist = new Artist();
        artist.setName("The Testers");

        assertNull(artist.getId());
        artistManager.createArtist(artist);

        assertNotNull(artist.getId());
        assertNotNull(artistManager.findArtist(1l));

        assertEquals(artist, artistManager.findArtist(1l));
    }

    /**
     * Test of getAllArtists method, of class ArtistManagerImpl.
     */
    @Test
    public void testGetAllArtists() {
        Artist artist1 = new Artist();
        artist1.setName("The Testers");
        artist1.setNote("Testing artist The Testers");
        Artist artist2 = new Artist();
        artist2.setName("Testing Unit");
        artist2.setNote("Testing Unit testing");

        assertNull(artist1.getId());
        assertNull(artist2.getId());
        artistManager.createArtist(artist1);
        artistManager.createArtist(artist2);
        assertEquals(artistManager.getAllArtists().size(), 2);

        List<Artist> expected = new ArrayList<>();
        expected.add(artist1);
        expected.add(artist2);
        List<Artist> test = artistManager.getAllArtists();

        assertArrayEquals(expected.toArray(), test.toArray());
        deepAssert(expected.toArray(), test.toArray());
    }

    /**
     * Test of findArtistByName method, of class ArtistManagerImpl.
     */
    @Test
    public void testFindArtistByNameWithUniqueName() {
        Artist artist1 = new Artist();
        artist1.setName("The Testers");
        artist1.setNote("Testing artist The Testers");
        Artist artist2 = new Artist();
        artist2.setName("Testing Unit");
        artist2.setNote("Testing Unit testing");

        assertNull(artist1.getId());
        assertNull(artist2.getId());
        artistManager.createArtist(artist1);
        artistManager.createArtist(artist2);
        assertEquals(artistManager.getAllArtists().size(), 2);


        List<Artist> expected = new ArrayList<>();
        expected.add(artist1);
        List<Artist> test = artistManager.findArtistByName("The Testers");

        assertArrayEquals(expected.toArray(), test.toArray());
        deepAssert(expected.toArray(), test.toArray());
    }

    /**
     * Test of findArtistByName method, of class ArtistManagerImpl.
     */
    @Test
    public void testFindArtistByNameWithMultipleSameNameArtist() {
        Artist artist1 = new Artist();
        artist1.setName("The Testers");
        artist1.setNote("Testing artist The Testers");
        Artist artist2 = new Artist();
        artist2.setName("The Testers");
        artist2.setNote("Testing Unit testing");

        assertNull(artist1.getId());
        assertNull(artist2.getId());
        artistManager.createArtist(artist1);
        artistManager.createArtist(artist2);
        assertEquals(artistManager.getAllArtists().size(), 2);

        List<Artist> expected = new ArrayList<>();
        expected.add(artist1);
        expected.add(artist2);
        List<Artist> test = artistManager.getAllArtists();

        assertArrayEquals(expected.toArray(), test.toArray());
        deepAssert(expected.toArray(), test.toArray());
    }

    /**
     * Test of findArtistByName method on empty database
     */
    @Test
    public void testFindArtistByNameOnEmptyDb() {
        assertEquals(artistManager.findArtistByName("The Testers").size(), 0);
    }

    private void deepAssert(Object[] arr1, Object[] arr2) {
        assertEquals(arr1.length, arr2.length);

        for (int i = 0; i < arr1.length; i++) {
            Artist artist1 = (Artist) arr1[i];
            Artist artist2 = (Artist) arr2[i];
            assertEquals(artist1.getId(), artist2.getId());
            assertEquals(artist1.getName(), artist2.getName());
            assertEquals(artist1.getNote(), artist2.getNote());
        }
    }
}
