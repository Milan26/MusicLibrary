package project.pa165.musiclibrary.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import project.pa165.musiclibrary.dto.SongDto;
import project.pa165.musiclibrary.exception.BadRequestException;
import project.pa165.musiclibrary.exception.SongNotFoundException;
import project.pa165.musiclibrary.services.SongService;
import project.pa165.musiclibrary.util.ErrorInfo;
import project.pa165.musiclibrary.util.Genre;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Arrays;
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

    @RequestMapping(value = "/genres", method = RequestMethod.GET)
    public List<Genre> getAllGenres() {
        return Arrays.asList(Genre.values());
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
        if (errors.hasErrors())
            throw new BadRequestException("Failed to map JSON to SongDto", errors);
        songService.createSong(song);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateSong(@Valid @RequestBody SongDto song, Errors errors) {
        if (errors.hasErrors())
            throw new BadRequestException("Failed to map JSON to SongDto", errors);
        songService.updateSong(song);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SongNotFoundException.class)
    public
    @ResponseBody
    ErrorInfo handleSongNotFoundException(SongNotFoundException ex) {
        return new ErrorInfo(404, ex.getMessage());
    }
}
