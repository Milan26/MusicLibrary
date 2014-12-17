package project.pa165.musiclibrary.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import project.pa165.musiclibrary.dto.SongDto;
import project.pa165.musiclibrary.exception.SongBadRequestException;
import project.pa165.musiclibrary.exception.SongNotFoundException;
import project.pa165.musiclibrary.services.SongService;
import project.pa165.musiclibrary.util.ErrorInfo;

import javax.inject.Inject;
import javax.validation.Valid;
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
    public List<SongDto> getAllSongs() {
        return songService.getAllSongs();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<SongDto> getSongsByTerm(@RequestParam("term") String term) {
        return getSongService().findSongByTitle(term);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SongDto getSongById(@PathVariable("id") Long id) throws SongNotFoundException {
        SongDto song = getSongService().findSong(id);
        if (song == null) throw new SongNotFoundException(id.toString());
        return song;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteSong(@PathVariable("id") Long id) {
        if (getSongService().findSong(id) == null) throw new SongNotFoundException(id.toString());
        songService.deleteSong(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public void createSong(@Valid @RequestBody SongDto song, Errors errors) {
        if (song == null || errors.hasErrors())
            throw new SongBadRequestException("Failed to map JSON to SongDto");
        songService.createSong(song);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateSong(@Valid @RequestBody SongDto song, Errors errors) {
        if (song == null || errors.hasErrors())
            throw new SongBadRequestException("Failed to map JSON to SongDto");
        songService.updateSong(song);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SongNotFoundException.class)
    public
    @ResponseBody
    ErrorInfo handleSongNotFoundException() {
        return new ErrorInfo(404, "Song could not be found");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({SongBadRequestException.class, MethodArgumentNotValidException.class})
    public
    @ResponseBody
    ErrorInfo handleSongBadRequestException() {
        return new ErrorInfo(400, "Song\'s method with bad request");
    }
}
