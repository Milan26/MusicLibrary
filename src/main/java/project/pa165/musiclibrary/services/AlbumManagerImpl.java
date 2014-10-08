/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.pa165.musiclibrary.services;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.pa165.musiclibrary.dao.AlbumDao;
import project.pa165.musiclibrary.entities.Album;

/**
 *
 * @author Milan
 */
@Service
public class AlbumManagerImpl implements AlbumManager {

    @Autowired
    private AlbumDao albumDao;

    @Override
    @Transactional
    public void createAlbum(Album album) {
        albumDao.create(album);
    }

    @Override
    @Transactional
    public void deleteAlbum(Long id) {
        albumDao.delete(id);
    }

    @Override
    @Transactional
    public void updateAlbum(Album album) {
        albumDao.update(album);
    }

    @Override
    @Transactional
    public Album findAlbum(Long id) {
        return albumDao.find(id);
    }

    @Override
    @Transactional
    public List<Album> getAllAlbums() {
        return albumDao.getAll();
    }

    @Override
    @Transactional
    public List<Album> findAlbumByTitle(String title) {
        return albumDao.findAlbumByTitle(title);
    }

}
