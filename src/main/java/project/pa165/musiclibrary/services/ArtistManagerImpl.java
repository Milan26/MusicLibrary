package project.pa165.musiclibrary.services;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.pa165.musiclibrary.dao.ArtistDao;
import project.pa165.musiclibrary.entities.Artist;

/**
 *
 * @author Matúš
 */
@Service
public class ArtistManagerImpl implements ArtistManager{
    @Autowired
    private ArtistDao artistDao;

    @Override
    @Transactional
    public void createArtist(final Artist artist) {
        artistDao.create(artist);
    }

    @Override
    @Transactional
    public void deleteArtist(final Long id) {
        artistDao.delete(id);
    }

    @Override
    @Transactional
    public void updateArtist(final Artist artist) {
        artistDao.update(artist);
    }

    @Override
    @Transactional
    public Artist findArtist(final Long id) {
        return artistDao.find(id);
    }

    @Override
    @Transactional
    public List<Artist> getAllArtists() {
        return artistDao.getAll();
    }

    @Override
    @Transactional
    public List<Artist> findArtistByName(final String name) {
        return artistDao.findArtistByName(name);
    }

}
