package project.pa165.musiclibrary.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import project.pa165.musiclibrary.dto.AlbumDto;
import project.pa165.musiclibrary.dto.ArtistDto;
import project.pa165.musiclibrary.dto.SongDto;
import project.pa165.musiclibrary.services.AlbumService;
import project.pa165.musiclibrary.services.ArtistService;
import project.pa165.musiclibrary.services.SongService;
import project.pa165.musiclibrary.util.Genre;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class AdministrationControllerTest {

    private AlbumDto album1;
    private AlbumDto album2;
    private SongDto song1;
    private SongDto song2;
    private ArtistDto artist1;
    private ArtistDto artist2;

    private MockMvc mockMvc;
    @Mock
    private AlbumService albumService;
    @Mock
    private SongService songService;
    @Mock
    private ArtistService artistService;
    @Mock
    private MessageSource messageSource;
    @InjectMocks
    private AdministrationController administrationController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        this.mockMvc = MockMvcBuilders.standaloneSetup(administrationController).setViewResolvers(viewResolver).build();

        album1 = createAlbumDto(1l, "Unity", "2-6-1991", "http://pathtocoverart.com", "album");
        album2 = createAlbumDto(2l, "Hello Uni", "1-1-2001", "http://blabla.com", "note2");
        song1 = createSongDto("Walk", (short) 1, 200, Genre.ROCK, 320, "test");
        song2 = createSongDto("Arlandria", (short) 2, 300, Genre.HOLIDAY, 128, "test");
        artist1 = createArtistDto(1l, "Alfa", "Testing artist1");
        artist2 = createArtistDto(2l, "Beta", "Testing artist2");

        when(albumService.getAllAlbums()).thenReturn(Arrays.asList(album1, album2));
        when(albumService.findAlbum(1l)).thenReturn(album1);
        when(songService.getAllSongs()).thenReturn(Arrays.asList(song1, song2));
        when(songService.findSong(1l)).thenReturn(song1);
        when(artistService.getAllArtists()).thenReturn(Arrays.asList(artist1, artist2));
        when(artistService.findArtist(1l)).thenReturn(artist1);
    }

    @Test
    public void testAllAlbums() throws Exception {
        mockMvc.perform(get("/admin/albums"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"))
                .andExpect(model().attributeDoesNotExist("songs"))
                .andExpect(model().attributeDoesNotExist("artists"))
                .andExpect(model().attributeDoesNotExist("message"))
                .andExpect(model().attributeExists("albums"))
                .andExpect(model().attribute("albums", Arrays.asList(album1, album2)));
        verify(albumService).getAllAlbums();
        verifyNoMoreInteractions(albumService);
    }

    @Test
    public void testDeleteAlbum() throws Exception {
        Long id = 1l;
        mockMvc.perform(post("/admin/albums/delete/" + id))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/albums"))
                .andExpect(view().name("redirect:/admin/albums"));
        verify(albumService).deleteAlbum(id);
        verifyNoMoreInteractions(albumService);
    }

    @Test
    public void testGetAlbumId() throws Exception {
        Long id = 1l;
        mockMvc.perform(get("/admin/albums/update")
                .param("id", id.toString()))
                .andExpect(status().isOk())
                .andExpect(view().name("admin-edit"))
                .andExpect(model().attributeDoesNotExist("song"))
                .andExpect(model().attributeDoesNotExist("artist"))
                .andExpect(model().attributeExists("album"))
                .andExpect(model().attribute("album", album1));
        verify(albumService).findAlbum(id);
        verifyNoMoreInteractions(albumService);
    }

    @Test
    public void testGetAlbumIdNull() throws Exception {
        mockMvc.perform(get("/admin/albums/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin-edit"))
                .andExpect(model().attributeDoesNotExist("song"))
                .andExpect(model().attributeDoesNotExist("artist"))
                .andExpect(model().attributeExists("album"))
                .andExpect(model().attribute("album", hasProperty("id", is(nullValue()))))
                .andExpect(model().attribute("album", hasProperty("title", is(isEmptyOrNullString()))))
                .andExpect(model().attribute("album", hasProperty("releaseDate", is(isEmptyOrNullString()))))
                .andExpect(model().attribute("album", hasProperty("coverArt", is(isEmptyOrNullString()))))
                .andExpect(model().attribute("album", hasProperty("note", is(isEmptyOrNullString()))))
                .andExpect(model().attribute("album", hasProperty("songs", is(nullValue()))));
        verifyZeroInteractions(albumService);
    }

    @Test
    public void testUpdateAlbum() throws Exception {
        Long id = 1l;
        mockMvc.perform(post("/admin/albums/update").param("id", id.toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/albums"))
                .andExpect(view().name("redirect:/admin/albums"))
                .andExpect(flash().attributeCount(1));
        verify(albumService).findAlbum(id);
        verify(albumService).updateAlbum(org.mockito.Mockito.any(AlbumDto.class));
        verifyNoMoreInteractions(albumService);
    }

    @Test
    public void testCreateAlbum() throws Exception {
        mockMvc.perform(post("/admin/albums/update"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/albums"))
                .andExpect(view().name("redirect:/admin/albums"))
                .andExpect(flash().attributeCount(1));
        verify(albumService).createAlbum(org.mockito.Mockito.any(AlbumDto.class));
        verifyNoMoreInteractions(albumService);
    }

    @Test
    public void testAllArtists() throws Exception {
        mockMvc.perform(get("/admin/artists"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"))
                .andExpect(model().attributeDoesNotExist("songs"))
                .andExpect(model().attributeDoesNotExist("albums"))
                .andExpect(model().attributeDoesNotExist("message"))
                .andExpect(model().attributeExists("artists"))
                .andExpect(model().attribute("artists", Arrays.asList(artist1, artist2)));
        verify(artistService).getAllArtists();
        verifyNoMoreInteractions(artistService);
    }

    @Test
    public void testDeleteArtist() throws Exception {
        Long id = 1l;
        mockMvc.perform(post("/admin/artists/delete/" + id))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/artists"))
                .andExpect(view().name("redirect:/admin/artists"));
        verify(artistService).deleteArtist(id);
        verifyNoMoreInteractions(artistService);
    }

    @Test
    public void testGetArtistId() throws Exception {
        Long id = 1l;
        mockMvc.perform(get("/admin/artists/update")
                .param("id", id.toString()))
                .andExpect(status().isOk())
                .andExpect(view().name("admin-edit"))
                .andExpect(model().attributeDoesNotExist("song"))
                .andExpect(model().attributeDoesNotExist("album"))
                .andExpect(model().attributeExists("artist"))
                .andExpect(model().attribute("artist", artist1));
        verify(artistService).findArtist(id);
        verifyNoMoreInteractions(artistService);
    }

    @Test
    public void testGetArtistIdNull() throws Exception {
        mockMvc.perform(get("/admin/artists/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin-edit"))
                .andExpect(model().attributeDoesNotExist("song"))
                .andExpect(model().attributeDoesNotExist("album"))
                .andExpect(model().attributeExists("artist"))
                .andExpect(model().attribute("artist", hasProperty("id", is(nullValue()))));
        verifyZeroInteractions(artistService);
    }

    @Test
    public void testUpdateArtist() throws Exception {
        Long id = 1l;
        mockMvc.perform(post("/admin/artists/update").param("id", id.toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/artists"))
                .andExpect(view().name("redirect:/admin/artists"))
                .andExpect(flash().attributeCount(1));
        verify(artistService).findArtist(id);
        verify(artistService).updateArtist(org.mockito.Mockito.any(ArtistDto.class));
        verifyNoMoreInteractions(artistService);
    }

    @Test
    public void testCreateArtist() throws Exception {
        mockMvc.perform(post("/admin/artists/update"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/artists"))
                .andExpect(view().name("redirect:/admin/artists"))
                .andExpect(flash().attributeCount(1));
        verify(artistService).createArtist(org.mockito.Mockito.any(ArtistDto.class));
        verifyNoMoreInteractions(artistService);
    }

    @Test
    public void testAllSongs() throws Exception {
        mockMvc.perform(get("/admin/songs"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"))
                .andExpect(model().attributeDoesNotExist("albums"))
                .andExpect(model().attributeDoesNotExist("artists"))
                .andExpect(model().attributeDoesNotExist("message"))
                .andExpect(model().attributeExists("songs"))
                .andExpect(model().attribute("songs", Arrays.asList(song1, song2)));
        verify(songService).getAllSongs();
        verifyNoMoreInteractions(songService);
    }

    @Test
    public void testDeleteSong() throws Exception {
        Long id = 1l;
        mockMvc.perform(post("/admin/songs/delete/" + id))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/songs"))
                .andExpect(view().name("redirect:/admin/songs"));
        verify(songService).deleteSong(id);
        verifyNoMoreInteractions(songService);
    }

    @Test
    public void testGetSongId() throws Exception {
        Long id = 1l;
        mockMvc.perform(get("/admin/songs/update")
                .param("id", id.toString()))
                .andExpect(status().isOk())
                .andExpect(view().name("admin-edit"))
                .andExpect(model().attributeDoesNotExist("artist"))
                .andExpect(model().attributeDoesNotExist("album"))
                .andExpect(model().attributeExists("song"))
                .andExpect(model().attribute("song", song1));
        verify(songService).findSong(id);
        verifyNoMoreInteractions(songService);
    }

    @Test
    public void testGetSongIdNull() throws Exception {
        mockMvc.perform(get("/admin/songs/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin-edit"))
                .andExpect(model().attributeDoesNotExist("artist"))
                .andExpect(model().attributeDoesNotExist("album"))
                .andExpect(model().attributeExists("song"))
                .andExpect(model().attribute("song", hasProperty("id", is(nullValue()))));
        verifyZeroInteractions(songService);
    }

    @Test
    public void testUpdateSong() throws Exception {
        Long id = 1l;
        mockMvc.perform(post("/admin/songs/update")
                .param("id", id.toString()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/songs"))
                .andExpect(view().name("redirect:/admin/songs"))
                .andExpect(flash().attributeCount(1));
        verify(songService).findSong(id);
        verify(songService).updateSong(org.mockito.Mockito.any(SongDto.class));
        verifyNoMoreInteractions(songService);
    }

    @Test
    public void testCreateSong() throws Exception {
        mockMvc.perform(post("/admin/songs/update"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/songs"))
                .andExpect(view().name("redirect:/admin/songs"))
                .andExpect(flash().attributeCount(1));
        verify(songService).createSong(org.mockito.Mockito.any(SongDto.class));
        verifyNoMoreInteractions(songService);
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

    private ArtistDto createArtistDto(Long id, String name, String note) {
        ArtistDto artist = new ArtistDto();
        artist.setId(id);
        artist.setAlias(name);
        artist.setNote(note);
        return artist;
    }
}