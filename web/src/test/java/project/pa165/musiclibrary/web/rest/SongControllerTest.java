package project.pa165.musiclibrary.web.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import project.pa165.musiclibrary.dto.SongDto;
import project.pa165.musiclibrary.services.AlbumService;
import project.pa165.musiclibrary.services.ArtistService;
import project.pa165.musiclibrary.util.Genre;
import project.pa165.musiclibrary.services.SongService;
import project.pa165.musiclibrary.web.util.RestAuthenticatorHelper;

import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class SongControllerTest {

    private SongDto song1;
    private SongDto song2;

    private MockMvc mockMvc;
    @Mock
    private SongService songService;
    @Mock
    private AlbumService albumService;
    @Mock
    private ArtistService artistService;
    @Mock
    private RestAuthenticatorHelper restAuthenticatorHelper;
    @InjectMocks
    private SongController songController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(songController).build();

        song1 = createSong(1l, "Walk", (short) 1, 200, Genre.ROCK, 320, "test");
        song2 = createSong(2l, "Arlandria Walking", (short) 2, 300, Genre.HOLIDAY, 128, "just song");

        when(songService.getAllSongs()).thenReturn(Arrays.asList(song1, song2));
        when(songService.findSongByTitle("walk")).thenReturn(Arrays.asList(song1, song2));
        when(songService.findSong(1l)).thenReturn(song1);
    }

    @Test
    public void getAllSongs() throws Exception {
        mockMvc.perform(get("/songs"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Walk"))
                .andExpect(jsonPath("$[0].trackNumber").value(1))
                .andExpect(jsonPath("$[0].duration").value(200))
                .andExpect(jsonPath("$[0].genre").value(Genre.ROCK.toString()))
                .andExpect(jsonPath("$[0].bitrate").value(320))
                .andExpect(jsonPath("$[0].note").value("test"))
                .andExpect(jsonPath("$[0].artist").value(nullValue()))
                .andExpect(jsonPath("$[0].album").value(nullValue()))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Arlandria Walking"))
                .andExpect(jsonPath("$[1].trackNumber").value(2))
                .andExpect(jsonPath("$[1].duration").value(300))
                .andExpect(jsonPath("$[1].genre").value(Genre.HOLIDAY.toString()))
                .andExpect(jsonPath("$[1].bitrate").value(128))
                .andExpect(jsonPath("$[1].note").value("just song"))
                .andExpect(jsonPath("$[1].artist").value(nullValue()))
                .andExpect(jsonPath("$[1].album").value(nullValue()));
        verify(songService).getAllSongs();
        verifyNoMoreInteractions(songService);
    }

    @Test
    public void testGetSongsByTerm() throws Exception {
        String term = "walk";
        mockMvc.perform(get("/songs/search")
                .param("term", term))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Walk"))
                .andExpect(jsonPath("$[0].trackNumber").value(1))
                .andExpect(jsonPath("$[0].duration").value(200))
                .andExpect(jsonPath("$[0].genre").value(Genre.ROCK.toString()))
                .andExpect(jsonPath("$[0].bitrate").value(320))
                .andExpect(jsonPath("$[0].note").value("test"))
                .andExpect(jsonPath("$[0].artist").value(nullValue()))
                .andExpect(jsonPath("$[0].album").value(nullValue()))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Arlandria Walking"))
                .andExpect(jsonPath("$[1].trackNumber").value(2))
                .andExpect(jsonPath("$[1].duration").value(300))
                .andExpect(jsonPath("$[1].genre").value(Genre.HOLIDAY.toString()))
                .andExpect(jsonPath("$[1].bitrate").value(128))
                .andExpect(jsonPath("$[1].note").value("just song"))
                .andExpect(jsonPath("$[1].artist").value(nullValue()))
                .andExpect(jsonPath("$[1].album").value(nullValue()));
        verify(songService).findSongByTitle(term);
        verifyNoMoreInteractions(songService);
    }

    @Test
    public void testGetSongById() throws Exception {
        Long id = 1l;
        mockMvc.perform(get("/songs/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Walk"))
                .andExpect(jsonPath("$.trackNumber").value(1))
                .andExpect(jsonPath("$.duration").value(200))
                .andExpect(jsonPath("$.genre").value(Genre.ROCK.toString()))
                .andExpect(jsonPath("$.bitrate").value(320))
                .andExpect(jsonPath("$.note").value("test"))
                .andExpect(jsonPath("$.artist").value(nullValue()))
                .andExpect(jsonPath("$.album").value(nullValue()));
        verify(songService).findSong(id);
        verifyNoMoreInteractions(songService);
    }

    @Test
    public void testDeleteSong() throws Exception {
        Long id = 1l;
        mockMvc.perform(delete("/songs/" + id))
                .andExpect(status().isOk());
        verify(songService).findSong(id);
        verify(songService).deleteSong(id);
        verifyNoMoreInteractions(songService);
    }

    @Test
    public void testCreateSong() throws Exception {
        mockMvc.perform(post("/songs")
                .content(convertObjectToJsonBytes(song1))
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isCreated());
        verify(songService).createSong(any(SongDto.class));
        verifyNoMoreInteractions(songService);
    }

    @Test
    public void testUpdateSong() throws Exception {
        Long album_id = 1l;
        Long artist_id = 1l;
        mockMvc.perform(put("/songs/" + album_id + "/" + artist_id)
                .content(convertObjectToJsonBytes(song1))
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk());
        verify(albumService).findAlbum(album_id);
        verify(artistService).findArtist(artist_id);
        verify(songService).updateSong(any(SongDto.class));
        verifyNoMoreInteractions(songService);
        verifyNoMoreInteractions(albumService);
        verifyNoMoreInteractions(artistService);
    }

    private SongDto createSong(Long id, String title, Short trackNumber, Integer length,
                               Genre genre, Integer bitrate, String note) {
        SongDto song = new SongDto();
        song.setId(id);
        song.setTitle(title);
        song.setTrackNumber(trackNumber);
        song.setDuration(length);
        song.setGenre(genre);
        song.setBitrate(bitrate);
        song.setNote(note);
        return song;
    }

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
}