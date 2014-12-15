package project.pa165.musiclibrary.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import project.pa165.musiclibrary.dto.SongDto;
import project.pa165.musiclibrary.exception.ServiceException;
import project.pa165.musiclibrary.exception.SongNotFoundException;
import project.pa165.musiclibrary.services.SongService;
import project.pa165.musiclibrary.util.ErrorInfo;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Milan
 */
@RestController
@RequestMapping(value = "/songs")
public class SongController {

    private SongService songService;

    public SongService getSongService() {
        return songService;
    }

    @Inject
    public void setSongService(SongService songService) {
        this.songService = songService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void getAllSongs() throws ServiceException{
        songService.getAllSongs();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<SongDto> getSongsByTerm(@RequestParam("term") String term) throws ServiceException {
        return getSongService().findSongByTitle(term);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SongDto getSongById(@PathVariable("id") Long id) throws ServiceException, SongNotFoundException {
        SongDto song = getSongService().findSong(id);
        if (song == null)
            throw new SongNotFoundException(id.toString());
        return song;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteSong(@PathVariable("id") Long id) throws ServiceException{
        songService.deleteSong(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void createSong(@RequestBody SongDto songDto) throws ServiceException{
        songService.createSong(songDto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateSong(@RequestBody SongDto songDto) throws ServiceException{
        songService.updateSong(songDto);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SongNotFoundException.class)
    public @ResponseBody
    ErrorInfo handleSongNotFoundException() {
        return new ErrorInfo(404, "Song could not be found");
    }
}
