package project.pa165.musiclibrary.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import project.pa165.musiclibrary.dto.AlbumDto;
import project.pa165.musiclibrary.exception.AlbumNotFoundException;
import project.pa165.musiclibrary.exception.ServiceException;
import project.pa165.musiclibrary.services.AlbumManager;
import project.pa165.musiclibrary.util.ErrorInfo;

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

    @Autowired
    public void setAlbumManager(AlbumManager albumManager) {
        this.albumManager = albumManager;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<AlbumDto> getAlbumsByTerm(@RequestParam("term") String term) throws ServiceException {
        return getAlbumManager().findAlbumByTitle(term);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AlbumDto getAlbumById(@PathVariable("id") Long id) throws ServiceException, AlbumNotFoundException {
        AlbumDto album = getAlbumManager().findAlbum(id);
        if (album == null)
            throw new AlbumNotFoundException(id.toString());
        return album;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AlbumNotFoundException.class)
    public @ResponseBody
    ErrorInfo handleAlbumNotFoundException() {
        return new ErrorInfo(404, "Album could not be found");
    }
}
