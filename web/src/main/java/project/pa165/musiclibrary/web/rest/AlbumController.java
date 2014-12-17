package project.pa165.musiclibrary.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import project.pa165.musiclibrary.dto.AlbumDto;
import project.pa165.musiclibrary.exception.AlbumBadRequestException;
import project.pa165.musiclibrary.exception.AlbumNotFoundException;
import project.pa165.musiclibrary.services.AlbumService;
import project.pa165.musiclibrary.util.ErrorInfo;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Milan
 */
@RestController
@RequestMapping(value = "/albums")
public class AlbumController {

    private AlbumService albumService;

    public AlbumService getAlbumService() {
        return albumService;
    }

    @Inject
    public void setAlbumService(AlbumService albumService) {
        this.albumService = albumService;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public List<AlbumDto> getAllAlbums() {
        return albumService.getAllAlbums();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<AlbumDto> getAlbumsByTerm(@RequestParam("term") String term) {
        return getAlbumService().findAlbumByTitle(term);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AlbumDto getAlbumById(@PathVariable("id") Long id) throws AlbumNotFoundException {
        AlbumDto album = getAlbumService().findAlbum(id);
        if (album == null) throw new AlbumNotFoundException(id.toString());
        return album;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteAlbum(@PathVariable("id") Long id) {
        if (getAlbumService().findAlbum(id) == null) throw new AlbumNotFoundException(id.toString());
        albumService.deleteAlbum(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void createAlbum(@Valid @RequestBody AlbumDto albumDto, Errors errors) {
        if (albumDto == null || errors.hasErrors())
            throw new AlbumBadRequestException("Failed to map JSON to AlbumDto");
        albumService.createAlbum(albumDto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateAlbum(@Valid @RequestBody AlbumDto albumDto, Errors errors) {
        if (albumDto == null || errors.hasErrors())
            throw new AlbumBadRequestException("Failed to map JSON to AlbumDto");
        albumService.updateAlbum(albumDto);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AlbumNotFoundException.class)
    public
    @ResponseBody
    ErrorInfo handleAlbumNotFoundException() {
        return new ErrorInfo(404, "Album could not be found");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AlbumBadRequestException.class)
    public
    @ResponseBody
    ErrorInfo handleAlbumBadRequestException() {
        return new ErrorInfo(400, "Album\'s method with bad request");
    }
}
