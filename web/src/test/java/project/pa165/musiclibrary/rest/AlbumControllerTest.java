package project.pa165.musiclibrary.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import project.pa165.musiclibrary.dto.AlbumDto;
import project.pa165.musiclibrary.exception.AlbumNotFoundException;
import project.pa165.musiclibrary.services.AlbumService;
import project.pa165.musiclibrary.web.rest.AlbumController;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class AlbumControllerTest {

    private MockMvc mockMvc;
    @Mock
    private AlbumService albumService;
    @InjectMocks
    private AlbumController albumController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(albumController).build();

        AlbumDto album1 = createAlbumDto(1l, "Unity", "2-6-1991", "http://pathtocoverart.com", "album");
        AlbumDto album2 = createAlbumDto(2l, "Hello Uni", "1-1-2001", "http://blabla.com", "note2");

        when(albumService.findAlbumByTitle("uni")).thenReturn(Arrays.asList(album1, album2));
        when(albumService.findAlbum(1l)).thenReturn(album1);
    }

    @Test
    public void testGetAlbumsByTerm() throws Exception {
        String term = "uni";
        mockMvc.perform(get("/albums/search" + "?term=" + term))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Unity"))
                .andExpect(jsonPath("$[0].releaseDate").value("2-6-1991"))
                .andExpect(jsonPath("$[0].coverArt").value("http://pathtocoverart.com"))
                .andExpect(jsonPath("$[0].note").value("album"))
                .andExpect(jsonPath("$[0].songs").value(nullValue()))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Hello Uni"))
                .andExpect(jsonPath("$[1].releaseDate").value("1-1-2001"))
                .andExpect(jsonPath("$[1].coverArt").value("http://blabla.com"))
                .andExpect(jsonPath("$[1].note").value("note2"))
                .andExpect(jsonPath("$[1].songs").value(nullValue()));
        verify(albumService).findAlbumByTitle(term);
    }

    @Test
    public void testGetAlbumById() throws Exception {
        Long id = 1l;
        mockMvc.perform(get("/albums/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Unity"))
                .andExpect(jsonPath("$.releaseDate").value("2-6-1991"))
                .andExpect(jsonPath("$.coverArt").value("http://pathtocoverart.com"))
                .andExpect(jsonPath("$.note").value("album"))
                .andExpect(jsonPath("$.songs").value(nullValue()));
        verify(albumService).findAlbum(id);
    }

    @Test
    public void testGetAlbumByIdNoMatch() throws Exception {
        when(albumService.findAlbum(any(Long.class))).thenThrow(new AlbumNotFoundException());
        Long id = 1l;
        mockMvc.perform(get("/albums/" + id))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("Album could not be found"));
        verify(albumService).findAlbum(id);
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
}