package project.pa165.musiclibrary.services;

import project.pa165.musiclibrary.dao.ArtistDao;
import project.pa165.musiclibrary.entities.Artist;
import project.pa165.musiclibrary.exception.PersistenceException;
import project.pa165.musiclibrary.exception.ServiceException;

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
    public void createArtist(final Artist artist) throws ServiceException {
        try {
            getArtistDao().create(artist);
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }
    }

    @Override
    public void deleteArtist(final Long id) throws ServiceException {
        try {
            getArtistDao().delete(id);
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }
    }

    @Override
    public void updateArtist(final Artist artist) throws ServiceException {
        try {
            getArtistDao().update(artist);
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }
    }

    @Override
    public Artist findArtist(final Long id) throws ServiceException {
        Artist artist = null;
        try {
            artist = getArtistDao().find(id);
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }
        return artist;
    }

    @Override
    public List<Artist> getAllArtists() throws ServiceException {
        List<Artist> allArtists = null;
        try {
            allArtists = getArtistDao().getAll();
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }
        return allArtists;
    }

    @Override
    public List<Artist> findArtistByName(final String name) throws ServiceException {
        List<Artist> allMatchedArtists = null;
        try {
            allMatchedArtists = getArtistDao().findArtistByName(name);
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }
        return allMatchedArtists;
    }

}
