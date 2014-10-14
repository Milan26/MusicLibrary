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

    @Inject
    private AlbumDao albumDao;

    @Override
    public void createAlbum(final Album album) {
        albumDao.create(album);
    }

    @Override
    public void deleteAlbum(final Long id) {
        albumDao.delete(id);
    }

    @Override
    public void updateAlbum(final Album album) {
        albumDao.update(album);
    }

    @Override
    public Album findAlbum(final Long id) {
        return albumDao.find(id);
    }

    @Override
    public List<Album> getAllAlbums() {
        return albumDao.getAll();
    }

    @Override
    public List<Album> findAlbumByTitle(final String title) {
        return albumDao.findAlbumByTitle(title);
    }

}
