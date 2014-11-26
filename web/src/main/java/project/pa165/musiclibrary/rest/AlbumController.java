package project.pa165.musiclibrary.rest;

import org.springframework.web.bind.annotation.*;
import project.pa165.musiclibrary.dto.AlbumDto;
import project.pa165.musiclibrary.exception.ServiceException;
import project.pa165.musiclibrary.services.AlbumManager;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Milan
 */
@RestController
@RequestMapping(value = "/music/albums")
public class AlbumController {

    private AlbumManager albumManager;

    public AlbumManager getAlbumManager() {
        return albumManager;
    }

    @Inject
    public void setAlbumManager(AlbumManager albumManager) {
        this.albumManager = albumManager;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<AlbumDto> getAlbumsByTerm(@RequestParam("term") String term) throws ServiceException {
        return getAlbumManager().findAlbumByTitle(term);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AlbumDto getAlbumById(@PathVariable("id") Long id) throws ServiceException {
        return getAlbumManager().findAlbum(id);
    }
}
