package project.pa165.musiclibrary.rest;

import org.springframework.web.bind.annotation.*;
import project.pa165.musiclibrary.dto.SongDto;
import project.pa165.musiclibrary.exception.ServiceException;
import project.pa165.musiclibrary.services.SongManager;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Milan
 */
@RestController
@RequestMapping(value = "/music/songs")
public class SongController {

    private SongManager songManager;

    public SongManager getSongManager() {
        return songManager;
    }

    @Inject
    public void setSongManager(SongManager songManager) {
        this.songManager = songManager;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<SongDto> getAlbumsByTerm(@RequestParam("term") String term) throws ServiceException {
        return getSongManager().findSongByTitle(term);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SongDto getAlbumById(@PathVariable("id") Long id) throws ServiceException {
        return getSongManager().findSong(id);
    }
}
