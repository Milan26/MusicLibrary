/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.pa165.musiclibrary.services;

import java.util.List;
import project.pa165.musiclibrary.entities.Album;

/**
 *
 * @author Milan
 */
public interface AlbumManager {
    
    void createAlbum(Album album);
    void deleteAlbum(Long id);
    void updateAlbum(Album album);
    Album findAlbum(Long id);
    List<Album> getAllAlbums();
    
    List<Album> findAlbumByTitle(String title);
}
