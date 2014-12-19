package project.pa165.musiclibrary.web.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import project.pa165.musiclibrary.dto.ArtistDto;
import project.pa165.musiclibrary.services.ArtistService;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Milan
 */
@RestController
@RequestMapping(value = "/artists")
public class ArtistController {

    private ArtistService artistService;

    public ArtistService getArtistService() {
        return artistService;
    }

    @Inject
    public void setArtistService(ArtistService artistService) {
        this.artistService = artistService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ArtistDto> getAllArtists() {
        return getArtistService().getAllArtists();
    }
}
