package project.pa165.musiclibrary.web.rest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import project.pa165.musiclibrary.dto.ArtistDto;
import project.pa165.musiclibrary.services.ArtistService;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Milan
 */
public class ArtistControllerTest {

    private ArtistDto artist1;
    private ArtistDto artist2;

    private MockMvc mockMvc;
    @Mock
    private ArtistService artistService;
    @InjectMocks
    private ArtistController artistController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(artistController).build();

        artist1 = createArtistDto(1l, "Alfa", "Testing artist1");
        artist2 = createArtistDto(2l, "Beta", "Testing artist2");

        when(artistService.getAllArtists()).thenReturn(Arrays.asList(artist1, artist2));
    }

    @Test
    public void getAllArtists() throws Exception {
        mockMvc.perform(get("/artists"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].alias").value("Alfa"))
                .andExpect(jsonPath("$[0].note").value("Testing artist1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].alias").value("Beta"))
                .andExpect(jsonPath("$[1].note").value("Testing artist2"));
        verify(artistService).getAllArtists();
        verifyNoMoreInteractions(artistService);
    }

    private ArtistDto createArtistDto(Long id, String name, String note) {
        ArtistDto artist = new ArtistDto();
        artist.setId(id);
        artist.setAlias(name);
        artist.setNote(note);
        return artist;
    }
}
