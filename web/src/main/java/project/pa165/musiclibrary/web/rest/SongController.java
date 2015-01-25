package project.pa165.musiclibrary.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import project.pa165.musiclibrary.dto.SongDto;
import project.pa165.musiclibrary.exception.BadRequestException;
import project.pa165.musiclibrary.exception.SongNotFoundException;
import project.pa165.musiclibrary.services.AlbumService;
import project.pa165.musiclibrary.services.ArtistService;
import project.pa165.musiclibrary.services.SongService;
import project.pa165.musiclibrary.util.ErrorInfo;
import project.pa165.musiclibrary.util.Genre;
import project.pa165.musiclibrary.web.util.RestAuthenticatorHelper;

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

    private ArtistService artistService;
    private AlbumService albumService;
    private SongService songService;
    private RestAuthenticatorHelper restAuthenticatorHelper;

    public SongService getSongService() {
        return songService;
    }

    @Inject
    public void setSongService(SongService songService) {
        this.songService = songService;
    }

    public ArtistService getArtistService() {
        return artistService;
    }

    @Inject
    public void setArtistService(ArtistService artistService) {
        this.artistService = artistService;
    }

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
    public List<SongDto> getAllSongs() {
        return getSongService().getAllSongs();
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
        getRestAuthenticatorHelper().authenticate();
        getSongService().deleteSong(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public void createSong(@Valid @RequestBody SongDto song, Errors errors) {
        if (errors.hasErrors()) throw new BadRequestException("Failed to map JSON to SongDto", errors);
        getRestAuthenticatorHelper().authenticate();
        getSongService().createSong(song);
    }

    @RequestMapping(value = "/{album_id}/{artist_id}", method = RequestMethod.PUT)
    public void updateSong(@PathVariable("album_id") Long albumId, @PathVariable("artist_id") Long artistId,
            @Valid @RequestBody SongDto song, Errors errors) {
        if (errors.hasErrors()) throw new BadRequestException("Failed to map JSON to SongDto", errors);
        if (albumId != null) song.setAlbum(getAlbumService().findAlbum(albumId));
        if (artistId != null) song.setArtist(getArtistService().findArtist(artistId));
        getRestAuthenticatorHelper().authenticate();
        getSongService().updateSong(song);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SongNotFoundException.class)
    public
    @ResponseBody
    ErrorInfo handleSongNotFoundException(SongNotFoundException ex) {
        return new ErrorInfo(404, ex.getMessage());
    }
}
