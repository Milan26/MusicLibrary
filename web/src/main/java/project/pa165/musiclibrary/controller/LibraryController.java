package project.pa165.musiclibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.pa165.musiclibrary.dto.AlbumDto;
import project.pa165.musiclibrary.dto.SongDto;
import project.pa165.musiclibrary.exception.ServiceException;
import project.pa165.musiclibrary.services.AlbumService;
import project.pa165.musiclibrary.services.SongService;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Milan
 */
@Controller
public class LibraryController {

    private AlbumService albumService;
    private SongService songService;

    public AlbumService getAlbumService() {
        return albumService;
    }

    @Inject
    public void setAlbumService(AlbumService albumService) {
        this.albumService = albumService;
    }

    public SongService getSongService() {
        return songService;
    }

    @Inject
    public void setSongService(SongService songService) {
        this.songService = songService;
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about() {
        return "about";
    }

    @RequestMapping(value = {"/", "/music", "/music/albums"}, method = RequestMethod.GET)
    public String browseMusicByAlbums(Model model) throws ServiceException {
        model.addAttribute("albums", allAlbums());
        return "albums";
    }

    @RequestMapping(value = "/music/songs", method = RequestMethod.GET)
    public String browseMusicBySongs(Model model) throws ServiceException {
        model.addAttribute("songs", allSongs());
        return "songs";
    }

    private List<AlbumDto> allAlbums() throws ServiceException {
        return getAlbumService().getAllAlbums();
    }

    private List<SongDto> allSongs() throws ServiceException {
        return getSongService().getAllSongs();
    }
}
