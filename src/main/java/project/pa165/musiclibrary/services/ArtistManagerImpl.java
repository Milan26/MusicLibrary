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

    @Inject
    private ArtistDao artistDao;

    @Override
    public void createArtist(final Artist artist) {
        artistDao.create(artist);
    }

    @Override
    public void deleteArtist(final Long id) {
        artistDao.delete(id);
    }

    @Override
    public void updateArtist(final Artist artist) {
        artistDao.update(artist);
    }

    @Override
    public Artist findArtist(final Long id) {
        return artistDao.find(id);
    }

    @Override
    public List<Artist> getAllArtists() {
        return artistDao.getAll();
    }

    @Override
    public List<Artist> findArtistByName(final String name) {
        return artistDao.findArtistByName(name);
    }

}
