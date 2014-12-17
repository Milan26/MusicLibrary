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
import project.pa165.musiclibrary.dto.AlbumDto;
import project.pa165.musiclibrary.exception.AlbumNotFoundException;
import project.pa165.musiclibrary.services.AlbumService;

import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class AlbumControllerTest {

    private AlbumDto album1;
    private AlbumDto album2;

    private MockMvc mockMvc;
    @Mock
    private AlbumService albumService;
    @InjectMocks
    private AlbumController albumController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(albumController).build();

        album1 = createAlbumDto(1l, "Unity", "02-06-1991", "http://pathtocoverart.com", "album");
        album2 = createAlbumDto(2l, "Hello Uni", "01-01-2001", "http://blabla.com", "note2");

        when(albumService.getAllAlbums()).thenReturn(Arrays.asList(album1, album2));
        when(albumService.findAlbumByTitle("uni")).thenReturn(Arrays.asList(album1, album2));
        when(albumService.findAlbum(1l)).thenReturn(album1);
    }

    @Test
    public void getAllAlbums() throws Exception {
        mockMvc.perform(get("/albums"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Unity"))
                .andExpect(jsonPath("$[0].releaseDate").value("02-06-1991"))
                .andExpect(jsonPath("$[0].coverArt").value("http://pathtocoverart.com"))
                .andExpect(jsonPath("$[0].note").value("album"))
                .andExpect(jsonPath("$[0].songs").value(nullValue()))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Hello Uni"))
                .andExpect(jsonPath("$[1].releaseDate").value("01-01-2001"))
                .andExpect(jsonPath("$[1].coverArt").value("http://blabla.com"))
                .andExpect(jsonPath("$[1].note").value("note2"))
                .andExpect(jsonPath("$[1].songs").value(nullValue()));
        verify(albumService).getAllAlbums();
        verifyNoMoreInteractions(albumService);
    }

    @Test
    public void testGetAlbumsByTerm() throws Exception {
        String term = "uni";
        mockMvc.perform(get("/albums/search")
                .param("term", term))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Unity"))
                .andExpect(jsonPath("$[0].releaseDate").value("02-06-1991"))
                .andExpect(jsonPath("$[0].coverArt").value("http://pathtocoverart.com"))
                .andExpect(jsonPath("$[0].note").value("album"))
                .andExpect(jsonPath("$[0].songs").value(nullValue()))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Hello Uni"))
                .andExpect(jsonPath("$[1].releaseDate").value("01-01-2001"))
                .andExpect(jsonPath("$[1].coverArt").value("http://blabla.com"))
                .andExpect(jsonPath("$[1].note").value("note2"))
                .andExpect(jsonPath("$[1].songs").value(nullValue()));
        verify(albumService).findAlbumByTitle(term);
        verifyNoMoreInteractions(albumService);
    }

    @Test
    public void testGetAlbumById() throws Exception {
        Long id = 1l;
        mockMvc.perform(get("/albums/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Unity"))
                .andExpect(jsonPath("$.releaseDate").value("02-06-1991"))
                .andExpect(jsonPath("$.coverArt").value("http://pathtocoverart.com"))
                .andExpect(jsonPath("$.note").value("album"))
                .andExpect(jsonPath("$.songs").value(nullValue()));
        verify(albumService).findAlbum(id);
        verifyNoMoreInteractions(albumService);
    }

    @Test
    public void testGetAlbumByIdNoMatch() throws Exception {
        when(albumService.findAlbum(any(Long.class))).thenThrow(new AlbumNotFoundException());
        Long id = 2l;
        mockMvc.perform(get("/albums/" + id))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("Album could not be found"));
        verify(albumService).findAlbum(id);
        verifyNoMoreInteractions(albumService);
    }

    @Test
    public void testDeleteAlbum() throws Exception {
        Long id = 1l;
        mockMvc.perform(delete("/albums/" + id))
                .andExpect(status().isOk());
        verify(albumService).findAlbum(id);
        verify(albumService).deleteAlbum(id);
        verifyNoMoreInteractions(albumService);
    }

    @Test
    public void testDeleteAlbumIdNoMatch() throws Exception {
        Long id = 2l;
        mockMvc.perform(delete("/albums/" + id))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("Album could not be found"));
        verify(albumService).findAlbum(id);
        verifyNoMoreInteractions(albumService);
    }

    @Test
    public void testCreateAlbum() throws Exception {
        mockMvc.perform(post("/albums")
                .content(convertObjectToJsonBytes(album1))
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk());
        verify(albumService).createAlbum(any(AlbumDto.class));
        verifyNoMoreInteractions(albumService);
    }

    @Test
    public void testCreateAlbumNull() throws Exception {
        mockMvc.perform(post("/albums")
                .content(convertObjectToJsonBytes(null))
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Album\'s method with bad request"));
        verifyZeroInteractions(albumService);
    }

    @Test
    public void testUpdateAlbum() throws Exception {
        mockMvc.perform(put("/albums")
                .content(convertObjectToJsonBytes(album1))
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk());
        verify(albumService).updateAlbum(any(AlbumDto.class));
        verifyNoMoreInteractions(albumService);
    }

    @Test
    public void testUpdateAlbumNull() throws Exception {
        mockMvc.perform(put("/albums")
                .content(convertObjectToJsonBytes(null))
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Album\'s method with bad request"));
        verifyZeroInteractions(albumService);
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

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
}