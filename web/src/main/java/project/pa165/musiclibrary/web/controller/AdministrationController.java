package project.pa165.musiclibrary.web.controller;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.pa165.musiclibrary.dto.AlbumDto;
import project.pa165.musiclibrary.dto.ArtistDto;
import project.pa165.musiclibrary.dto.SongDto;
import project.pa165.musiclibrary.services.AlbumService;
import project.pa165.musiclibrary.services.ArtistService;
import project.pa165.musiclibrary.services.SongService;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Locale;

/**
 * @author Milan
 */
@Controller
@RequestMapping(value = "/admin")
public class AdministrationController {

    private AlbumService albumService;
    private ArtistService artistService;
    private SongService songService;
    private MessageSource messageSource;

    public AlbumService getAlbumService() {
        return albumService;
    }

    @Inject
    public void setAlbumService(AlbumService albumService) {
        this.albumService = albumService;
    }

    public ArtistService getArtistService() {
        return artistService;
    }

    @Inject
    public void setArtistService(ArtistService artistService) {
        this.artistService = artistService;
    }

    public SongService getSongService() {
        return songService;
    }

    @Inject
    public void setSongService(SongService songService) {
        this.songService = songService;
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    @Inject
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/albums", method = RequestMethod.GET)
    public ModelAndView allAlbums() {
        ModelAndView model = new ModelAndView();
        model.addObject("albums", getAlbumService().getAllAlbums());
        model.setViewName("admin");
        return model;
    }

    @RequestMapping(value = "/albums/delete/{id}", method = RequestMethod.POST)
    public String deleteAlbum(@PathVariable("id") Long id) {
        getAlbumService().deleteAlbum(id);
        return "redirect:/admin/albums";
    }

    @RequestMapping(value = "/albums/update", method = RequestMethod.GET)
    public ModelAndView getAlbum(@RequestParam(value = "id", required = false) Long id) {
        ModelAndView model = new ModelAndView();
        if (id == null) {
            model.addObject("album", new AlbumDto());
        } else {
            model.addObject("album", getAlbumService().findAlbum(id));
        }
        model.setViewName("admin-edit");
        return model;
    }

    @RequestMapping(value = "/albums/update", method = RequestMethod.POST)
    public String updateAlbum(@RequestParam(value = "id", required = false) Long id,
                                   @Valid @ModelAttribute("album") AlbumDto album,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes,
                                   Locale locale) {
        if (bindingResult.hasErrors()) return "admin-edit";
        MessageSourceResolvable source;
        if (id == null) {
            getAlbumService().createAlbum(album);
            source = new DefaultMessageSourceResolvable("admin.album.create.success");
        } else {
            copyHiddenFields(getAlbumService().findAlbum(id), album);
            getAlbumService().updateAlbum(album);
            source = new DefaultMessageSourceResolvable("admin.album.edit.success");
        }
        redirectAttributes.addFlashAttribute("message", getMessageSource().getMessage(source, locale));
        return "redirect:/admin/albums";
    }

    @RequestMapping(value = "/artists", method = RequestMethod.GET)
    public ModelAndView allArtists() {
        ModelAndView model = new ModelAndView();
        model.addObject("artists", getArtistService().getAllArtists());
        model.setViewName("admin");
        return model;
    }

    @RequestMapping(value = "/artists/delete/{id}", method = RequestMethod.POST)
    public String deleteArtist(@PathVariable("id") Long id) {
        getArtistService().deleteArtist(id);
        return "redirect:/admin/artists";
    }

    @RequestMapping(value = "/artists/update", method = RequestMethod.GET)
    public ModelAndView getArtist(@RequestParam(value = "id", required = false) Long id) {
        ModelAndView model = new ModelAndView();
        if (id == null) {
            model.addObject("artist", new ArtistDto());
        } else {
            model.addObject("artist", getArtistService().findArtist(id));
        }
        model.setViewName("admin-edit");
        return model;
    }

    @RequestMapping(value = "/artists/update", method = RequestMethod.POST)
    public String updateArtist(@RequestParam(value = "id", required = false) Long id,
                              @Valid @ModelAttribute("artist") ArtistDto artist,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              Locale locale) {
        if (bindingResult.hasErrors()) return "admin-edit";
        MessageSourceResolvable source;
        if (id == null) {
            getArtistService().createArtist(artist);
            source = new DefaultMessageSourceResolvable("admin.artist.create.success");
        } else {
            copyHiddenFields(getArtistService().findArtist(id), artist);
            getArtistService().updateArtist(artist);
            source = new DefaultMessageSourceResolvable("admin.artist.edit.success");
        }
        redirectAttributes.addFlashAttribute("message", getMessageSource().getMessage(source, locale));
        return "redirect:/admin/artists";
    }

    @RequestMapping(value = "/songs", method = RequestMethod.GET)
    public ModelAndView allSongs() {
        ModelAndView model = new ModelAndView();
        model.addObject("songs", getSongService().getAllSongs());
        model.setViewName("admin");
        return model;
    }

    @RequestMapping(value = "/songs/delete/{id}", method = RequestMethod.POST)
    public String deleteSong(@PathVariable("id") Long id) {
        getSongService().deleteSong(id);
        return "redirect:/admin/songs";
    }

    @RequestMapping(value = "/songs/update", method = RequestMethod.GET)
    public ModelAndView getSong(@RequestParam(value = "id", required = false) Long id) {
        ModelAndView model = new ModelAndView();
        if (id == null) {
            model.addObject("song", new SongDto());
        } else {
            model.addObject("song", getSongService().findSong(id));
        }
        model.addObject("artists", getArtistService().getAllArtists());
        model.addObject("albums", getAlbumService().getAllAlbums());
        model.setViewName("admin-edit");
        return model;
    }

    @RequestMapping(value = "/songs/update", method = RequestMethod.POST)
    public String updateSong(@RequestParam(value = "id", required = false) Long id,
                               @Valid @ModelAttribute("song") SongDto song,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               Locale locale) {
        if (bindingResult.hasErrors()) return "admin-edit";
        MessageSourceResolvable source;
        if (id == null) {
            getSongService().createSong(song);
            source = new DefaultMessageSourceResolvable("admin.song.create.success");
        } else {
            copyHiddenFields(getSongService().findSong(id), song);
            getSongService().updateSong(song);
            source = new DefaultMessageSourceResolvable("admin.song.edit.success");
        }
        redirectAttributes.addFlashAttribute("message", getMessageSource().getMessage(source, locale));
        return "redirect:/admin/songs";
    }

    private void copyHiddenFields(AlbumDto album, AlbumDto modelAlbum) {
        modelAlbum.setId(album.getId());
        modelAlbum.setSongs(album.getSongs());
    }

    private void copyHiddenFields(ArtistDto artist, ArtistDto modelArtist) {
        modelArtist.setId(artist.getId());
        modelArtist.setSongs(artist.getSongs());
    }

    private void copyHiddenFields(SongDto song, SongDto modelSong) {
        modelSong.setId(song.getId());
        if (modelSong.getAlbum() != null)
            modelSong.setAlbum(getAlbumService().findAlbum(modelSong.getAlbum().getId()));
        if (modelSong.getArtist() != null)
            modelSong.setArtist(getArtistService().findArtist(modelSong.getArtist().getId()));
    }
}
