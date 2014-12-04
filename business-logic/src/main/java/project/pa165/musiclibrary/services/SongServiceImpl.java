package project.pa165.musiclibrary.services;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.pa165.musiclibrary.dao.SongDao;
import project.pa165.musiclibrary.dto.SongDto;
import project.pa165.musiclibrary.entities.Song;
import project.pa165.musiclibrary.exception.PersistenceException;
import project.pa165.musiclibrary.exception.ServiceException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex
 */
@Service
@Transactional
public class SongServiceImpl implements SongService {

    private SongDao songDao;
    private DozerBeanMapper dozerBeanMapper;

    public SongDao getSongDao() {
        return songDao;
    }

    @Autowired
    public void setSongDao(SongDao songDao) {
        this.songDao = songDao;
    }

    public DozerBeanMapper getDozerBeanMapper() {
        return dozerBeanMapper;
    }

    @Autowired
    public void setDozerBeanMapper(DozerBeanMapper dozerBeanMapper) {
        this.dozerBeanMapper = dozerBeanMapper;
    }

    @Override
    public void createSong(final SongDto songDto) throws ServiceException {
        Song song = null;
        if (songDto != null) song = getDozerBeanMapper().map(songDto, Song.class);
        try {
            getSongDao().create(song);
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }
    }

    @Override
    public void deleteSong(final Long id) throws ServiceException {
        try {
            getSongDao().delete(id);
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }
    }

    @Override
    public void updateSong(final SongDto songDto) throws ServiceException {
        Song song = null;
        if (songDto != null) song = getDozerBeanMapper().map(songDto, Song.class);
        try {
            getSongDao().update(song);
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }
    }

    @Override
    public SongDto findSong(final Long id) throws ServiceException {
        Song song = null;
        try {
            song = getSongDao().find(id);
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }

        return song != null ? getDozerBeanMapper().map(song, SongDto.class) : null;
    }

    @Override
    public List<SongDto> getAllSongs() throws ServiceException {
        List<Song> allSongs = null;
        try {
            allSongs = getSongDao().getAll();
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }

        return getMappedSongDtoCollection(allSongs);
    }

    @Override
    public List<SongDto> findSongByTitle(final String title) throws ServiceException {
        List<Song> allMatchedSongs = null;
        try {
            allMatchedSongs = getSongDao().findSongByTitle(title);
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }

        return getMappedSongDtoCollection(allMatchedSongs);
    }

    private List<SongDto> getMappedSongDtoCollection(List<Song> songs) {
        List<SongDto> mappedCollection = new ArrayList<>();
        for (Song song : songs) {
            mappedCollection.add(getDozerBeanMapper().map(song, SongDto.class));
        }
        return mappedCollection;
    }
}
