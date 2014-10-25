package project.pa165.musiclibrary.services;

import org.dozer.DozerBeanMapper;
import project.pa165.musiclibrary.dao.AlbumDao;
import project.pa165.musiclibrary.dto.AlbumDto;
import project.pa165.musiclibrary.entities.Album;
import project.pa165.musiclibrary.exception.DaoException;

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
public class AlbumManagerImpl implements AlbumManager {

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
    public void createAlbum(final AlbumDto albumDto) {
        Album album = null;
        try {
            if (albumDto != null) album = getDozerBeanMapper().map(albumDto, Album.class);
            getAlbumDao().create(album);
        } catch (DaoException daoEx) {

        }
    }

    @Override
    public void deleteAlbum(final Long id) {
        try {
            getAlbumDao().delete(id);
        } catch (DaoException daoEx) {

        }
    }

    @Override
    public void updateAlbum(final AlbumDto albumDto) {
        Album album = null;
        try {
            if (albumDto != null) album = getDozerBeanMapper().map(albumDto, Album.class);
            getAlbumDao().update(album);
        } catch (DaoException daoEx) {

        }
    }

    @Override
    public AlbumDto findAlbum(final Long id) {
        Album album = null;
        try {
            album = getAlbumDao().find(id);
        } catch (DaoException daoEx) {

        }
        return album != null ? getDozerBeanMapper().map(album, AlbumDto.class) : null;
    }

    @Override
    public List<AlbumDto> getAllAlbums() {
        List<Album> allAlbums = null;
        try {
            allAlbums = getAlbumDao().getAll();
        } catch (DaoException daoEx) {

        }
        return getMappedAlbumDtoCollection(allAlbums);
    }

    @Override
    public List<AlbumDto> findAlbumByTitle(final String title) {
        List<Album> allMatchedAlbums = null;
        try {
            allMatchedAlbums = getAlbumDao().findAlbumByTitle(title);
        } catch (DaoException daoEx) {

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
