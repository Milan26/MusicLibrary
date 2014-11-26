package project.pa165.musiclibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.pa165.musiclibrary.dto.AlbumDto;
import project.pa165.musiclibrary.dto.SongDto;
import project.pa165.musiclibrary.entities.Genre;
import project.pa165.musiclibrary.exception.ServiceException;
import project.pa165.musiclibrary.services.AlbumManager;
import project.pa165.musiclibrary.services.SongManager;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Milan
 */
@Controller
@RequestMapping("/music")
public class LibraryController {

    private AlbumManager albumManager;
    private SongManager songManager;

    public AlbumManager getAlbumManager() {
        return albumManager;
    }

    @Inject
    public void setAlbumManager(AlbumManager albumManager) {
        this.albumManager = albumManager;
    }

    public SongManager getSongManager() {
        return songManager;
    }

    @Inject
    public void setSongManager(SongManager songManager) {
        this.songManager = songManager;
    }

    @ModelAttribute("albums")
    public List<AlbumDto> allAlbums() throws ServiceException {
        return getAlbumManager().getAllAlbums();
    }

    @ModelAttribute("songs")
    public List<SongDto> allSongs() throws ServiceException {
        return getSongManager().getAllSongs();
    }

    @ModelAttribute("genres")
    public List<Genre> allGenres() throws ServiceException {
        return new ArrayList<>(Arrays.asList(Genre.values()));
    }

    @RequestMapping(method = RequestMethod.GET)
    public String browseMusic() {
        return browseMusicByAlbums();
    }

    @RequestMapping(value = "/albums", method = RequestMethod.GET)
    public String browseMusicByAlbums() {
        return "albums";
    }

    @RequestMapping(value = "/songs", method = RequestMethod.GET)
    public String browseMusicBySongs() {
        return "songs";
    }
}
