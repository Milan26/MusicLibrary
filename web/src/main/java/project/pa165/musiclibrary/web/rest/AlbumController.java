package project.pa165.musiclibrary.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import project.pa165.musiclibrary.dto.AlbumDto;
import project.pa165.musiclibrary.exception.BadRequestException;
import project.pa165.musiclibrary.exception.AlbumNotFoundException;
import project.pa165.musiclibrary.services.AlbumService;
import project.pa165.musiclibrary.util.ErrorInfo;
import project.pa165.musiclibrary.web.util.RestAuthenticatorHelper;

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
    private RestAuthenticatorHelper restAuthenticatorHelper;

    public AlbumService getAlbumService() {
        return albumService;
    }

    @Inject
    public void setAlbumService(AlbumService albumService) {
        this.albumService = albumService;
    }

    public RestAuthenticatorHelper getRestAuthenticatorHelper() {
        return restAuthenticatorHelper;
    }

    @Inject
    public void setRestAuthenticatorHelper(RestAuthenticatorHelper restAuthenticatorHelper) {
        this.restAuthenticatorHelper = restAuthenticatorHelper;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<AlbumDto> getAllAlbums() {
        return getAlbumService().getAllAlbums();
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
        getRestAuthenticatorHelper().authenticate();
        getAlbumService().deleteAlbum(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public void createAlbum(@Valid @RequestBody AlbumDto album, Errors errors) {
        if (errors.hasErrors())
            throw new BadRequestException("Failed to map JSON to AlbumDto", errors);
        getRestAuthenticatorHelper().authenticate();
        getAlbumService().createAlbum(album);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateAlbum(@Valid @RequestBody AlbumDto album, Errors errors) {
        if (errors.hasErrors())
            throw new BadRequestException("Failed to map JSON to AlbumDto", errors);
        getRestAuthenticatorHelper().authenticate();
        getAlbumService().updateAlbum(album);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AlbumNotFoundException.class)
    public
    @ResponseBody
    ErrorInfo handleAlbumNotFoundException(AlbumNotFoundException ex) {
        return new ErrorInfo(404, ex.getMessage());
    }
}
