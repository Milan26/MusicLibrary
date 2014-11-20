package project.pa165.musiclibrary.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.pa165.musiclibrary.dto.AlbumDto;
import project.pa165.musiclibrary.entities.Genre;
import project.pa165.musiclibrary.exception.ServiceException;
import project.pa165.musiclibrary.services.AlbumManager;

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

    public AlbumManager getAlbumManager() {
        return albumManager;
    }

    @Inject
    public void setAlbumManager(AlbumManager albumManager) {
        this.albumManager = albumManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String browseMusic() {
        return "music";
    }

    @ModelAttribute("albums")
    public List<AlbumDto> allAlbums() throws ServiceException {
        return getAlbumManager().getAllAlbums();
    }

    @ModelAttribute("genres")
    public List<Genre> allGenres() throws ServiceException {
        return new ArrayList<>(Arrays.asList(Genre.values()));
    }

}
