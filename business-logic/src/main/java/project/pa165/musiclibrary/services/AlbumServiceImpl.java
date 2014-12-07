package project.pa165.musiclibrary.services;

import org.dozer.DozerBeanMapper;
import project.pa165.musiclibrary.dao.AlbumDao;
import project.pa165.musiclibrary.dto.AlbumDto;
import project.pa165.musiclibrary.entities.Album;
import project.pa165.musiclibrary.exception.PersistenceException;
import project.pa165.musiclibrary.exception.ServiceException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of AlbumManager
 *
 * @author Milan
 */
@Named
@Transactional
public class AlbumServiceImpl implements AlbumService {

    private AlbumDao albumDao;
    private DozerBeanMapper dozerBeanMapper;

    public AlbumDao getAlbumDao() {
        return albumDao;
    }

    @Inject
    public void setAlbumDao(AlbumDao albumDao) {
        this.albumDao = albumDao;
    }

    public DozerBeanMapper getDozerBeanMapper() {
        return dozerBeanMapper;
    }

    @Inject
    public void setDozerBeanMapper(DozerBeanMapper dozerBeanMapper) {
        this.dozerBeanMapper = dozerBeanMapper;
    }

    @Override
    public void createAlbum(final AlbumDto albumDto) throws ServiceException {
        Album album = null;
        if (albumDto != null) album = getDozerBeanMapper().map(albumDto, Album.class);
        try {
            getAlbumDao().create(album);
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }
    }

    @Override
    public void deleteAlbum(final Long id) throws ServiceException {
        try {
            getAlbumDao().delete(id);
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }
    }

    @Override
    public void updateAlbum(final AlbumDto albumDto) throws ServiceException {
        Album album = null;
        if (albumDto != null) album = getDozerBeanMapper().map(albumDto, Album.class);
        try {
            getAlbumDao().update(album);
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }
    }

    @Override
    public AlbumDto findAlbum(final Long id) throws ServiceException {
        Album album = null;
        try {
            album = getAlbumDao().find(id);
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }

        return album != null ? getDozerBeanMapper().map(album, AlbumDto.class) : null;
    }

    @Override
    public List<AlbumDto> getAllAlbums() throws ServiceException {
        List<Album> allAlbums = null;
        try {
            allAlbums = getAlbumDao().getAll();
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }

        return getMappedAlbumDtoCollection(allAlbums);
    }

    @Override
    public List<AlbumDto> findAlbumByTitle(final String title) throws ServiceException {
        List<Album> allMatchedAlbums = null;
        try {
            allMatchedAlbums = getAlbumDao().findAlbumByTitle(title);
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }

        return getMappedAlbumDtoCollection(allMatchedAlbums);
    }

    private List<AlbumDto> getMappedAlbumDtoCollection(List<Album> albums) {
        List<AlbumDto> mappedCollection = new ArrayList<>();
        for (Album album : albums) {
            mappedCollection.add(getDozerBeanMapper().map(album, AlbumDto.class));
        }
        return mappedCollection;
    }
}
