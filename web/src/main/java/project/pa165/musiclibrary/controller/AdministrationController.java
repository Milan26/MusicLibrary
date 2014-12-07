package project.pa165.musiclibrary.controller;

import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.pa165.musiclibrary.dto.AlbumDto;
import project.pa165.musiclibrary.dto.ArtistDto;
import project.pa165.musiclibrary.dto.SongDto;
import project.pa165.musiclibrary.exception.ServiceException;
import project.pa165.musiclibrary.services.AlbumService;
import project.pa165.musiclibrary.services.ArtistService;
import project.pa165.musiclibrary.services.SongService;
import project.pa165.musiclibrary.services.UserService;

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
    private UserService userService;
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

    public UserService getUserService() {
        return userService;
    }

    @Inject
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    @Inject
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String admin() {
        return "admin";
    }

    @RequestMapping(value = "/albums", method = RequestMethod.GET)
    public ModelAndView adminAlbums() throws ServiceException {
        ModelAndView model = new ModelAndView();
        model.addObject("albums", getAlbumService().getAllAlbums());
        model.setViewName("admin");
        return model;
    }

    @RequestMapping(value = "/albums/delete/{id}", method = RequestMethod.POST)
    public String adminAlbumsDelete(@PathVariable("id") Long id) throws ServiceException {
        getAlbumService().deleteAlbum(id);
        return "redirect:/admin/albums";
    }

    @RequestMapping(value = "/albums/update/{id}", method = RequestMethod.GET)
    public ModelAndView adminGetAlbum(@PathVariable("id") Long id) throws ServiceException {
        ModelAndView model = new ModelAndView();
        AlbumDto album = getAlbumService().findAlbum(id);
        model.addObject("album", album);
        //        model.addObject("songs", getSongService().getAllSongs());
        model.setViewName("admin-edit");
        return model;
    }

    @RequestMapping(value = "/albums/update/{id}", method = RequestMethod.POST)
    public String adminAlbumUpdate(@PathVariable("id") Long id,
                                   @Valid @ModelAttribute("album") AlbumDto modelAlbum,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes,
                                   Locale locale) throws ServiceException {

        if (bindingResult.hasErrors()) return "admin-edit";
        AlbumDto album = getAlbumService().findAlbum(id);
        copyHiddenFields(album, modelAlbum);
        getAlbumService().updateAlbum(modelAlbum);
        redirectAttributes.addFlashAttribute("message",
                getMessageSource().getMessage(new DefaultMessageSourceResolvable("admin.album.edit.success"), locale));
        return "redirect:/admin/albums";
    }

    @RequestMapping(value = "/albums/update", method = RequestMethod.GET)
    public ModelAndView adminGetAlbum() throws ServiceException {
        ModelAndView model = new ModelAndView();
        model.addObject("album", new AlbumDto());
        model.setViewName("admin-edit");
        return model;
    }

    @RequestMapping(value = "/albums/update", method = RequestMethod.POST)
    public String adminAlbumUpdate(@Valid @ModelAttribute("album") AlbumDto modelAlbum,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes,
                                   Locale locale) throws ServiceException {

        if (bindingResult.hasErrors()) return "admin-edit";
        getAlbumService().createAlbum(modelAlbum);
        redirectAttributes.addFlashAttribute("message",
                getMessageSource().getMessage(new DefaultMessageSourceResolvable("admin.album.create.success"), locale));
        return "redirect:/admin/albums";
    }

    @RequestMapping(value = "/artists", method = RequestMethod.GET)
    public ModelAndView adminArtists() throws ServiceException {
        ModelAndView model = new ModelAndView();
        model.addObject("artists", getArtistService().getAllArtists());
        model.setViewName("admin");
        return model;
    }

    @RequestMapping(value = "/artists/delete/{id}", method = RequestMethod.POST)
    public String adminArtistsDelete(@PathVariable("id") Long id) throws ServiceException {
        getArtistService().deleteArtist(id);
        return "redirect:/admin/artists";
    }

    @RequestMapping(value = "/artists/update/{id}", method = RequestMethod.GET)
    public ModelAndView adminGetArtist(@PathVariable("id") Long id) throws ServiceException {
        ModelAndView model = new ModelAndView();
        model.addObject("artist", getArtistService().findArtist(id));
        //        model.addObject("songs", getSongService().getAllSongs());
        model.setViewName("admin-edit");
        return model;
    }

    @RequestMapping(value = "/artists/update/{id}", method = RequestMethod.POST)
    public String adminArtistUpdate(@PathVariable("id") Long id,
                                    @Valid @ModelAttribute("artist") ArtistDto modelArtist,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes,
                                    Locale locale) throws ServiceException {

        if (bindingResult.hasErrors()) return "admin-edit";

        ArtistDto artist = getArtistService().findArtist(id);
        copyHiddenFields(artist, modelArtist);
        getArtistService().updateArtist(modelArtist);
        redirectAttributes.addFlashAttribute("message",
                getMessageSource().getMessage(new DefaultMessageSourceResolvable("admin.artist.edit.success"), locale));

        return "redirect:/admin/artists";
    }

    @RequestMapping(value = "/artists/update", method = RequestMethod.GET)
    public ModelAndView adminGetArtist() throws ServiceException {
        ModelAndView model = new ModelAndView();
        model.addObject("artist", new ArtistDto());
        model.setViewName("admin-edit");
        return model;
    }

    @RequestMapping(value = "/artists/update", method = RequestMethod.POST)
    public String adminArtistUpdate(@Valid @ModelAttribute("artist") ArtistDto modelArtist,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes,
                                    Locale locale) throws ServiceException {

        if (bindingResult.hasErrors()) return "admin-edit";
        getArtistService().createArtist(modelArtist);
        redirectAttributes.addFlashAttribute("message",
                getMessageSource().getMessage(new DefaultMessageSourceResolvable("admin.artist.create.success"), locale));
        return "redirect:/admin/artists";
    }

    @RequestMapping(value = "/songs", method = RequestMethod.GET)
    public ModelAndView adminSongs() throws ServiceException {
        ModelAndView model = new ModelAndView();
        model.addObject("songs", getSongService().getAllSongs());
        model.setViewName("admin");
        return model;
    }

    @RequestMapping(value = "/songs/delete/{id}", method = RequestMethod.POST)
    public String adminSongsDelete(@PathVariable("id") Long id) throws ServiceException {
        getSongService().deleteSong(id);
        return "redirect:/admin/songs";
    }

    @RequestMapping(value = "/songs/update/{id}", method = RequestMethod.GET)
    public ModelAndView adminGetSong(@PathVariable("id") Long id) throws ServiceException {
        ModelAndView model = new ModelAndView();
        model.addObject("song", getSongService().findSong(id));
        model.addObject("artists", getArtistService().getAllArtists());
        model.addObject("albums", getAlbumService().getAllAlbums());
        model.setViewName("admin-edit");
        return model;
    }

    @RequestMapping(value = "/songs/update/{id}", method = RequestMethod.POST)
    public String adminSongUpdate(@PathVariable("id") Long id,
                                  @Valid @ModelAttribute("song") SongDto modelSong,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes,
                                  Locale locale) throws ServiceException {

        if (bindingResult.hasErrors()) return "admin-edit";

        SongDto song = getSongService().findSong(id);
        copyHiddenFields(song, modelSong);
        getSongService().updateSong(modelSong);
        redirectAttributes.addFlashAttribute("message",
                getMessageSource().getMessage(new DefaultMessageSourceResolvable("admin.song.edit.success"), locale));

        return "redirect:/admin/songs";
    }

    @RequestMapping(value = "/songs/update", method = RequestMethod.GET)
    public ModelAndView adminGetSong() throws ServiceException {
        ModelAndView model = new ModelAndView();
        model.addObject("song", new SongDto());
        model.addObject("artists", getArtistService().getAllArtists());
        model.addObject("albums", getAlbumService().getAllAlbums());
        model.setViewName("admin-edit");
        return model;
    }

    @RequestMapping(value = "/songs/update", method = RequestMethod.POST)
    public String adminSongUpdate(@Valid @ModelAttribute("song") SongDto modelSong,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes,
                                  Locale locale) throws ServiceException {

        if (bindingResult.hasErrors()) {
            return "admin-edit";
        }
        getSongService().createSong(modelSong);
        redirectAttributes.addFlashAttribute("message",
                getMessageSource().getMessage(new DefaultMessageSourceResolvable("admin.song.create.success"), locale));
        return "redirect:/admin/songs";
    }

    //    @RequestMapping(value = "/users", method = RequestMethod.GET)
    //    public ModelAndView adminUsers() throws ServiceException {
    //        ModelAndView model = new ModelAndView();
    //        model.addObject("users", getUserService().getAllUsers());
    //        model.setViewName("admin");
    //        return model;
    //    }

    private void copyHiddenFields(AlbumDto album, AlbumDto modelAlbum) {
        modelAlbum.setId(album.getId());
        modelAlbum.setSongs(album.getSongs());
    }

    private void copyHiddenFields(ArtistDto artist, ArtistDto modelArtist) {
        modelArtist.setId(artist.getId());
        modelArtist.setSongs(artist.getSongs());
    }

    private void copyHiddenFields(SongDto song, SongDto modelSong) throws ServiceException {
        modelSong.setId(song.getId());
        modelSong.setAlbum(getAlbumService().findAlbum(modelSong.getAlbum().getId()));
        modelSong.setArtist(getArtistService().findArtist(modelSong.getArtist().getId()));
    }
}
