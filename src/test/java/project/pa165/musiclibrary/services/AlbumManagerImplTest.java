package project.pa165.musiclibrary.services;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.joda.time.LocalDate;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import project.pa165.musiclibrary.entities.Album;

/**
 *
 * @author Milan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AlbumManagerImplTest {

    @Autowired
    private AlbumManager albumManager;

    /**
     * Test of createAlbum method, of class AlbumServiceImpl.
     */
    @Test
    public void testCreateAlbum() {

        Album album = new Album();
        album.setTitle("Unity");
        album.setReleaseDate(new LocalDate(1991, 2, 6).toDate());

        assertNull(album.getId());
        albumManager.createAlbum(album);
        assertNotNull(album.getId());
    }

    /**
     *
     */
    @Test
    public void testCreatedAlbumProperties() {

        Album album = new Album();
        album.setTitle("Unity");
        album.setReleaseDate(new LocalDate(1991, 2, 6).toDate());
        album.setCoverArt("http://pathtocoverart.com");
        album.setNote("Just testing Unity album");

        assertNull(album.getId());
        albumManager.createAlbum(album);
        Long id = album.getId();
        assertNotNull(id);

        Album actual = albumManager.findAlbum(id);
        assertEquals(album.getTitle(), actual.getTitle());
        assertEquals(album.getReleaseDate(), actual.getReleaseDate());
        assertEquals(album.getCoverArt(), actual.getCoverArt());
        assertEquals(album.getNote(), actual.getNote());
        assertNull(album.getSongs());
    }

    /**
     * Test of deleteAlbum method, of class AlbumServiceImpl.
     */
    @Test
    public void testDeleteAlbum() {
        Album album = new Album();
        album.setTitle("Unity");
        album.setReleaseDate(new LocalDate(1991, 2, 6).toDate());

        assertNull(album.getId());
        albumManager.createAlbum(album);

        albumManager.deleteAlbum(album.getId());
        assertNull(albumManager.findAlbum(album.getId()));
    }

    /**
     * Test of updateAlbum method, of class AlbumServiceImpl.
     */
    @Test
    public void testUpdateAlbum() {
        Album album = new Album();
        album.setTitle("Unity");
        album.setReleaseDate(new LocalDate(1991, 2, 6).toDate());

        assertNull(album.getId());
        albumManager.createAlbum(album);
        assertNotNull(album.getId());

        Long id = album.getId();
        Album actual = albumManager.findAlbum(id);
        assertNull(actual.getCoverArt());
        assertNull(actual.getNote());

        album.setCoverArt("http://pathtocoverart.com");
        album.setNote("Just testing Unity album");

        albumManager.updateAlbum(album);
        actual = albumManager.findAlbum(id);

        assertNotNull(actual.getCoverArt());
        assertNotNull(actual.getNote());

        assertEquals(album, actual);
    }

    /**
     *
     */
    @Test
    public void testFindAlbumOnEmptyDb() {
        assertNull(albumManager.findAlbum(1l));
    }

    /**
     * Test of findAlbum method, of class AlbumServiceImpl.
     */
    @Test
    public void testFindAlbum() {
        Album album = new Album();
        album.setTitle("Unity");
        album.setReleaseDate(new LocalDate(1991, 2, 6).toDate());

        assertNull(album.getId());
        albumManager.createAlbum(album);

        assertNotNull(album.getId());
        assertNotNull(albumManager.findAlbum(1l));

        assertEquals(album, albumManager.findAlbum(1l));
    }

    /**
     * Test of getAllAlbums method, of class AlbumManagerImpl.
     */
    @Test
    public void testGetAllAlbums() {
        Album album1 = new Album();
        album1.setTitle("Unity");
        album1.setReleaseDate(new LocalDate(1991, 2, 6).toDate());
        album1.setCoverArt("http://pathtocoverart.com");
        album1.setNote("Just testing Unity album");
        Album album2 = new Album();
        album2.setTitle("Hello");
        album2.setReleaseDate(new LocalDate(2001, 1, 1).toDate());
        album1.setCoverArt("http://blabla.com");
        album1.setNote("I am hungry");

        assertNull(album1.getId());
        assertNull(album2.getId());
        albumManager.createAlbum(album1);
        albumManager.createAlbum(album2);
        assertEquals(albumManager.getAllAlbums().size(), 2);

        List<Album> expected = new ArrayList<>();
        expected.add(album1);
        expected.add(album2);
        List<Album> actual = albumManager.getAllAlbums();

        assertArrayEquals(expected.toArray(), actual.toArray());
        deepAssert(expected.toArray(), actual.toArray());
    }

    /**
     * Test of findAlbumByTitle method, of class AlbumManagerImpl.
     */
    @Test
    public void testFindAlbumByTitleWithUniqueTitle() {
        Album album1 = new Album();
        album1.setTitle("Unity");
        album1.setReleaseDate(new LocalDate(1991, 2, 6).toDate());
        album1.setCoverArt("http://pathtocoverart.com");
        album1.setNote("Just testing Unity album");
        Album album2 = new Album();
        album2.setTitle("Hello");
        album2.setReleaseDate(new LocalDate(2001, 1, 1).toDate());
        album1.setCoverArt("http://blabla.com");
        album1.setNote("I am hungry");

        assertNull(album1.getId());
        assertNull(album2.getId());
        albumManager.createAlbum(album1);
        albumManager.createAlbum(album2);
        assertEquals(albumManager.getAllAlbums().size(), 2);

        List<Album> expected = new ArrayList<>();
        expected.add(album1);
        List<Album> actual = albumManager.findAlbumByTitle("Unity");

        assertArrayEquals(expected.toArray(), actual.toArray());
        deepAssert(expected.toArray(), actual.toArray());
    }
    
    /**
     * Test of findAlbumByTitle method, of class AlbumManagerImpl.
     */
    @Test
    public void testFindAlbumByTitleWithMultipleSameTitleAlbum() {
        Album album1 = new Album();
        album1.setTitle("Unity");
        album1.setReleaseDate(new LocalDate(1991, 2, 6).toDate());
        album1.setCoverArt("http://pathtocoverart.com");
        album1.setNote("Just testing Unity album");
        Album album2 = new Album();
        album2.setTitle("Unity");
        album2.setReleaseDate(new LocalDate(2001, 1, 1).toDate());
        album1.setCoverArt("http://blabla.com");
        album1.setNote("I am hungry");

        assertNull(album1.getId());
        assertNull(album2.getId());
        albumManager.createAlbum(album1);
        albumManager.createAlbum(album2);
        assertEquals(albumManager.getAllAlbums().size(), 2);

        List<Album> expected = new ArrayList<>();
        expected.add(album1);
        expected.add(album2);
        List<Album> actual = albumManager.getAllAlbums();

        assertArrayEquals(expected.toArray(), actual.toArray());
        deepAssert(expected.toArray(), actual.toArray());
    }

    /**
     *
     */
    @Test
    public void testFindAlbumByTitleOnEmptyDb() {
        assertEquals(albumManager.findAlbumByTitle("Unity").size(), 0);
    }
    
    private void deepAssert(Object[] arr1, Object[] arr2) {
        assertEquals(arr1.length, arr2.length);

        for (int i = 0; i < arr1.length; i++) {
            Album album1 = (Album) arr1[i];
            Album album2 = (Album) arr2[i];
            assertEquals(album1.getId(), album2.getId());
            assertEquals(album1.getTitle(), album2.getTitle());
            assertEquals(album1.getReleaseDate(), album2.getReleaseDate());
            assertEquals(album1.getCoverArt(), album2.getCoverArt());
            assertEquals(album1.getNote(), album2.getNote());
        }
    }

}
