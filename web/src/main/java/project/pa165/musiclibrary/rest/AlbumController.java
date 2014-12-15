package project.pa165.musiclibrary.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import project.pa165.musiclibrary.dto.AlbumDto;
import project.pa165.musiclibrary.entities.Album;
import project.pa165.musiclibrary.exception.AlbumNotFoundException;
import project.pa165.musiclibrary.exception.ServiceException;
import project.pa165.musiclibrary.services.AlbumService;
import project.pa165.musiclibrary.util.ErrorInfo;

import javax.inject.Inject;
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
    public void getAllAlbums() throws ServiceException{
        albumService.getAllAlbums();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<AlbumDto> getAlbumsByTerm(@RequestParam("term") String term) throws ServiceException {
        return getAlbumService().findAlbumByTitle(term);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AlbumDto getAlbumById(@PathVariable("id") Long id) throws ServiceException, AlbumNotFoundException {
        AlbumDto album = getAlbumService().findAlbum(id);
        if (album == null)
            throw new AlbumNotFoundException(id.toString());
        return album;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteAlbum(@PathVariable("id") Long id) throws ServiceException{
        albumService.deleteAlbum(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void createAlbum(@RequestBody AlbumDto albumDto) throws ServiceException{
        albumService.createAlbum(albumDto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateSong(@RequestBody AlbumDto albumDto) throws ServiceException{
        albumService.updateAlbum(albumDto);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AlbumNotFoundException.class)
    public @ResponseBody
    ErrorInfo handleAlbumNotFoundException() {
        return new ErrorInfo(404, "Album could not be found");
    }
}
