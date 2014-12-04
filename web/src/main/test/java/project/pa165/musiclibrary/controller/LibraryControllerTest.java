package project.pa165.musiclibrary.controller;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import project.pa165.musiclibrary.dto.AlbumDto;
import project.pa165.musiclibrary.dto.SongDto;
import project.pa165.musiclibrary.util.Genre;
import project.pa165.musiclibrary.exception.ServiceException;
import project.pa165.musiclibrary.services.AlbumService;
import project.pa165.musiclibrary.services.SongService;

import java.util.Arrays;
import java.util.Date;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(MockitoJUnitRunner.class)
public class LibraryControllerTest {

    private AlbumDto album1;
    private AlbumDto album2;
    private SongDto song1;
    private SongDto song2;

    private MockMvc mockMvc;
    @Mock
    private AlbumService albumService;
    @Mock
    private SongService songService;
    @InjectMocks
    private LibraryController libraryController;

    @Before
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        this.mockMvc = MockMvcBuilders.standaloneSetup(libraryController).setViewResolvers(viewResolver).build();

        album1 = createAlbumDto(1l, "Unity", new LocalDate(1991, 2, 6).toDate(), "http://pathtocoverart.com", "album");
        album2 = createAlbumDto(2l, "Hello Uni", new LocalDate(2001, 1, 1).toDate(), "http://blabla.com", "I am hungry");
        song1 = createSongDto("Walk", (short) 1, 200, Genre.ROCK, 320, "test");
        song2 = createSongDto("Arlandria", (short) 2, 300, Genre.HOLIDAY, 128, "test");

        when(albumService.getAllAlbums()).thenReturn(Arrays.asList(album1, album2));
        when(songService.getAllSongs()).thenReturn(Arrays.asList(song1, song2));
    }

    @Test
    public void testAbout() throws Exception {
        mockMvc.perform(get("/about"))
                .andExpect(status().isOk())
                .andExpect(view().name("about"))
                .andExpect(model().attributeDoesNotExist("albums"))
                .andExpect(model().attributeDoesNotExist("songs"));
    }

    @Test
    public void testBrowseMusicByAlbums() throws Exception {
        mockMvc.perform(get("/","/music","/music/albums"))
                .andExpect(status().isOk())
                .andExpect(view().name("albums"))
                .andExpect(model().attributeDoesNotExist("songs"))
                .andExpect(model().attributeExists("albums"))
                .andExpect(model().attribute("albums", Arrays.asList(album1, album2)));
        verify(albumService).getAllAlbums();
        verifyNoMoreInteractions(albumService);
    }

    @Test
    public void testBrowseMusicBySongs() throws Exception {
        mockMvc.perform(get("/music/songs"))
                .andExpect(status().isOk())
                .andExpect(view().name("songs"))
                .andExpect(model().attributeDoesNotExist("albums"))
                .andExpect(model().attributeExists("songs"))
                .andExpect(model().attribute("songs", Arrays.asList(song1, song2)));
        verify(songService).getAllSongs();
        verifyNoMoreInteractions(songService);
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

    private SongDto createSongDto(String title, Short trackNumber, Integer length, Genre genre, Integer bitrate, String note) {
        SongDto song = new SongDto();
        song.setTitle(title);
        song.setTrackNumber(trackNumber);
        song.setDuration(length);
        song.setGenre(genre);
        song.setBitrate(bitrate);
        song.setNote(note);
        return song;
    }
}