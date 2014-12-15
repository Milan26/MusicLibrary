package project.pa165.musiclibrary.services;

import org.dozer.DozerBeanMapper;
import project.pa165.musiclibrary.dao.SongDao;
import project.pa165.musiclibrary.dto.SongDto;
import project.pa165.musiclibrary.entities.Song;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex
 */
@Named
@Transactional
public class SongServiceImpl implements SongService {

    private SongDao songDao;
    private DozerBeanMapper dozerBeanMapper;

    public SongDao getSongDao() {
        return songDao;
    }

    @Inject
    public void setSongDao(SongDao songDao) {
        this.songDao = songDao;
    }

    public DozerBeanMapper getDozerBeanMapper() {
        return dozerBeanMapper;
    }

    @Inject
    public void setDozerBeanMapper(DozerBeanMapper dozerBeanMapper) {
        this.dozerBeanMapper = dozerBeanMapper;
    }

    @Override
    public void createSong(final SongDto songDto) {
        Song song = null;
        if (songDto != null) song = getDozerBeanMapper().map(songDto, Song.class);
        getSongDao().create(song);
    }

    @Override
    public void deleteSong(final Long id) {
        getSongDao().delete(id);
    }

    @Override
    public void updateSong(final SongDto songDto) {
        Song song = null;
        if (songDto != null) song = getDozerBeanMapper().map(songDto, Song.class);
        getSongDao().update(song);
    }

    @Override
    public SongDto findSong(final Long id) {
        Song song = getSongDao().find(id);
        return song != null ? getDozerBeanMapper().map(song, SongDto.class) : null;
    }

    @Override
    public List<SongDto> getAllSongs() {
        return getMappedSongDtoCollection(getSongDao().getAll());
    }

    @Override
    public List<SongDto> findSongByTitle(final String title) {
        return getMappedSongDtoCollection(getSongDao().findSongByTitle(title));
    }

    private List<SongDto> getMappedSongDtoCollection(List<Song> songs) {
        List<SongDto> mappedCollection = new ArrayList<>();
        for (Song song : songs) {
            mappedCollection.add(getDozerBeanMapper().map(song, SongDto.class));
        }
        return mappedCollection;
    }
}
