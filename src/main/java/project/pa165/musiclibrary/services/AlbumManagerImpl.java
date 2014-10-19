package project.pa165.musiclibrary.services;

import project.pa165.musiclibrary.dao.AlbumDao;
import project.pa165.musiclibrary.entities.Album;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
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

    public AlbumDao getAlbumDao() {
        return albumDao;
    }

    @Inject
    public void setAlbumDao(AlbumDao albumDao) {
        this.albumDao = albumDao;
    }

    @Override
    public void createAlbum(final Album album) {
        getAlbumDao().create(album);
    }

    @Override
    public void deleteAlbum(final Long id) {
        getAlbumDao().delete(id);
    }

    @Override
    public void updateAlbum(final Album album) {
        getAlbumDao().update(album);
    }

    @Override
    public Album findAlbum(final Long id) {
        return getAlbumDao().find(id);
    }

    @Override
    public List<Album> getAllAlbums() {
        return getAlbumDao().getAll();
    }

    @Override
    public List<Album> findAlbumByTitle(final String title) {
        return getAlbumDao().findAlbumByTitle(title);
    }

}
