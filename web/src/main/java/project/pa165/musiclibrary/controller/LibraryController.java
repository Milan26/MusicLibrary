package project.pa165.musiclibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.pa165.musiclibrary.dto.AlbumDto;
import project.pa165.musiclibrary.dto.SongDto;
import project.pa165.musiclibrary.exception.ServiceException;
import project.pa165.musiclibrary.services.AlbumManager;
import project.pa165.musiclibrary.services.SongManager;

import java.util.List;

/**
 * @author Milan
 */
@Controller
public class LibraryController {

    private AlbumManager albumManager;
    private SongManager songManager;

    public AlbumManager getAlbumManager() {
        return albumManager;
    }

    @Autowired
    public void setAlbumManager(AlbumManager albumManager) {
        this.albumManager = albumManager;
    }

    public SongManager getSongManager() {
        return songManager;
    }

    @Autowired
    public void setSongManager(SongManager songManager) {
        this.songManager = songManager;
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about() {
        return "about";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String browseMusicRoot(Model model) throws ServiceException {
        return browseMusicByAlbums(model);
    }

    @RequestMapping(value = "/music", method = RequestMethod.GET)
    public String browseMusic(Model model) throws ServiceException {
        return browseMusicByAlbums(model);
    }

    @RequestMapping(value = "/music/albums", method = RequestMethod.GET)
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
        return getAlbumManager().getAllAlbums();
    }

    private List<SongDto> allSongs() throws ServiceException {
        return getSongManager().getAllSongs();
    }
}
