package project.pa165.musiclibrary.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import project.pa165.musiclibrary.dto.SongDto;
import project.pa165.musiclibrary.exception.ServiceException;
import project.pa165.musiclibrary.exception.SongNotFoundException;
import project.pa165.musiclibrary.services.SongService;
import project.pa165.musiclibrary.util.ErrorInfo;

import java.util.List;

/**
 * @author Milan
 */
@RestController
@RequestMapping(value = "/music/songs")
public class SongController {

    private SongService songService;

    public SongService getSongService() {
        return songService;
    }

    @Autowired
    public void setSongService(SongService songService) {
        this.songService = songService;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<SongDto> getAlbumsByTerm(@RequestParam("term") String term) throws ServiceException {
        return getSongService().findSongByTitle(term);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SongDto getAlbumById(@PathVariable("id") Long id) throws ServiceException, SongNotFoundException {
        SongDto song = getSongService().findSong(id);
        if (song == null)
            throw new SongNotFoundException(id.toString());
        return song;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SongNotFoundException.class)
    public @ResponseBody
    ErrorInfo handleSongNotFoundException() {
        return new ErrorInfo(404, "Song could not be found");
    }
}
