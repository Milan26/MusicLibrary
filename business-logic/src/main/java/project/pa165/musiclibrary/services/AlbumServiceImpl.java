package project.pa165.musiclibrary.services;

import org.dozer.DozerBeanMapper;
import project.pa165.musiclibrary.dao.AlbumDao;
import project.pa165.musiclibrary.dto.AlbumDto;
import project.pa165.musiclibrary.entities.Album;

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
    public void createAlbum(final AlbumDto albumDto) {
        Album album = null;
        if (albumDto != null) album = getDozerBeanMapper().map(albumDto, Album.class);
        getAlbumDao().create(album);
    }

    @Override
    public void deleteAlbum(final Long id) {
        getAlbumDao().delete(id);
    }

    @Override
    public void updateAlbum(final AlbumDto albumDto) {
        Album album = null;
        if (albumDto != null) album = getDozerBeanMapper().map(albumDto, Album.class);
        getAlbumDao().update(album);
    }

    @Override
    public AlbumDto findAlbum(final Long id) {
        Album album = getAlbumDao().find(id);
        return album != null ? getDozerBeanMapper().map(album, AlbumDto.class) : null;
    }

    @Override
    public List<AlbumDto> getAllAlbums() {
        return getMappedAlbumDtoCollection(getAlbumDao().getAll());
    }

    @Override
    public List<AlbumDto> findAlbumByTitle(final String title) {
        return getMappedAlbumDtoCollection(getAlbumDao().findAlbumByTitle(title));
    }

    private List<AlbumDto> getMappedAlbumDtoCollection(List<Album> albums) {
        List<AlbumDto> mappedCollection = new ArrayList<>();
        for (Album album : albums) {
            mappedCollection.add(getDozerBeanMapper().map(album, AlbumDto.class));
        }
        return mappedCollection;
    }
}
