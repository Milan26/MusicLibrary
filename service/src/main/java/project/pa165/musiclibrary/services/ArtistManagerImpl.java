package project.pa165.musiclibrary.services;

import project.pa165.musiclibrary.dao.ArtistDao;
import project.pa165.musiclibrary.entities.Artist;
import project.pa165.musiclibrary.exception.DaoException;

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
        try {
            getArtistDao().create(artist);
        } catch (DaoException e) {

        }
    }

    @Override
    public void deleteArtist(final Long id) {
        try {
            getArtistDao().delete(id);
        } catch (DaoException e) {

        }
    }

    @Override
    public void updateArtist(final Artist artist) {
        try {
            getArtistDao().update(artist);
        } catch (DaoException e) {

        }
    }

    @Override
    public Artist findArtist(final Long id) {
        Artist artist = null;
        try {
            artist = getArtistDao().find(id);
        } catch (DaoException e) {

        }
        return artist;
    }

    @Override
    public List<Artist> getAllArtists() {
        List<Artist> allArtists = null;
        try {
            allArtists = getArtistDao().getAll();
        } catch (DaoException e) {

        }
        return allArtists;
    }

    @Override
    public List<Artist> findArtistByName(final String name) {
        List<Artist> allMatchedArtists = null;
        try {
            allMatchedArtists = getArtistDao().findArtistByName(name);
        } catch (DaoException e) {

        }
        return allMatchedArtists;
    }

}
