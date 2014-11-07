package project.pa165.musiclibrary.services;

import java.util.ArrayList;
import project.pa165.musiclibrary.dao.ArtistDao;
import project.pa165.musiclibrary.dto.ArtistDto;
import project.pa165.musiclibrary.exception.PersistenceException;
import project.pa165.musiclibrary.exception.ServiceException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;
import org.dozer.DozerBeanMapper;
import project.pa165.musiclibrary.entities.Artist;

/**
 * @author Matúš
 */
@Named
@Transactional
public class ArtistManagerImpl implements ArtistManager {

    private ArtistDao artistDao;
    private DozerBeanMapper dozerBeanMapper;
    
    public ArtistDao getArtistDao() {
        return artistDao;
    }

    @Inject
    public void setArtistDao(ArtistDao artistDao) {
        this.artistDao = artistDao;
    }
    
    public DozerBeanMapper getDozerBeanMapper() {
        return dozerBeanMapper;
    }
    
    @Inject
    public void setDozerBeanMapper(DozerBeanMapper dozerBeanMapper) {
        this.dozerBeanMapper = dozerBeanMapper;
    }

    @Override
    public void createArtist(final ArtistDto artistDto) throws ServiceException {
        Artist artist = null;
        if(artistDto != null) artist = getDozerBeanMapper().map(artistDto, Artist.class);
        
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
    public void updateArtist(final ArtistDto artistDto) throws ServiceException {
        Artist artist = null;
        if(artistDto != null) artist = getDozerBeanMapper().map(artistDto, Artist.class);
        try {
            getArtistDao().update(artist);
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }
    }

    @Override
    public ArtistDto findArtist(final Long id) throws ServiceException {
        Artist artist = null;
        try {
            artist = getArtistDao().find(id);
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }
        return artist != null ? getDozerBeanMapper().map(artist, ArtistDto.class) : null;
    }

    @Override
    public List<ArtistDto> getAllArtists() throws ServiceException {
        List<Artist> allArtists = null;
        try {
            allArtists = getArtistDao().getAll();
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }
        return getMappedArtistDtoCollection(allArtists);
    }

    @Override
    public List<ArtistDto> findArtistByName(final String name) throws ServiceException {
        List<Artist> allMatchedArtists = null;
        try {
            allMatchedArtists = getArtistDao().findArtistByName(name);
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }
        return getMappedArtistDtoCollection(allMatchedArtists);
    }
    
    private List<ArtistDto> getMappedArtistDtoCollection(List<Artist> artists) {
        List<ArtistDto> mappedCollection = new ArrayList<>();
        for (Artist artist : artists) {
            mappedCollection.add(getDozerBeanMapper().map(artist, ArtistDto.class));
        }
        return mappedCollection;
    }
}
