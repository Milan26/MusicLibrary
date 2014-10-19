package project.pa165.musiclibrary.services;

import project.pa165.musiclibrary.dao.ArtistDao;
import project.pa165.musiclibrary.entities.Artist;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Matúš
 */
@Named
@Transactional
public class ArtistManagerImpl implements ArtistManager {

    private ArtistDao artistDao;

    public ArtistDao getArtistDao() {
        return artistDao;
    }

    @Inject
    public void setArtistDao(ArtistDao artistDao) {
        this.artistDao = artistDao;
    }

    @Override
    public void createArtist(final Artist artist) {
        getArtistDao().create(artist);
    }

    @Override
    public void deleteArtist(final Long id) {
        getArtistDao().delete(id);
    }

    @Override
    public void updateArtist(final Artist artist) {
        getArtistDao().update(artist);
    }

    @Override
    public Artist findArtist(final Long id) {
        return getArtistDao().find(id);
    }

    @Override
    public List<Artist> getAllArtists() {
        return getArtistDao().getAll();
    }

    @Override
    public List<Artist> findArtistByName(final String name) {
        return getArtistDao().findArtistByName(name);
    }

}
