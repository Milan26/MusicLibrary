package project.pa165.musiclibrary.services;

import org.dozer.DozerBeanMapper;
import project.pa165.musiclibrary.dao.ArtistDao;
import project.pa165.musiclibrary.dto.ArtistDto;
import project.pa165.musiclibrary.entities.Artist;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Matúš
 */
@Named
@Transactional
public class ArtistServiceImpl implements ArtistService {

    private ArtistDao artistDao;
    private DozerBeanMapper dozerBeanMapper;

    public ArtistDao getArtistDao() {
        return artistDao;
    }

    @Inject
    public void setArtistDao(ArtistDao artistDao) {
        this.artistDao = artistDao;
    }

    public DozerBeanMapper getDozerBeanMapper() {
        return dozerBeanMapper;
    }

    @Inject
    public void setDozerBeanMapper(DozerBeanMapper dozerBeanMapper) {
        this.dozerBeanMapper = dozerBeanMapper;
    }

    @Override
    public void createArtist(final ArtistDto artistDto) {
        Artist artist = null;
        if (artistDto != null) artist = getDozerBeanMapper().map(artistDto, Artist.class);
        getArtistDao().create(artist);
    }

    @Override
    public void deleteArtist(final Long id) {
        getArtistDao().delete(id);
    }

    @Override
    public void updateArtist(final ArtistDto artistDto) {
        Artist artist = null;
        if (artistDto != null) artist = getDozerBeanMapper().map(artistDto, Artist.class);
        getArtistDao().update(artist);
    }

    @Override
    public ArtistDto findArtist(final Long id) {
        Artist artist = getArtistDao().find(id);
        return artist != null ? getDozerBeanMapper().map(artist, ArtistDto.class) : null;
    }

    @Override
    public List<ArtistDto> getAllArtists() {
        return getMappedArtistDtoCollection(getArtistDao().getAll());
    }

    @Override
    public List<ArtistDto> findArtistByName(final String name) {
        return getMappedArtistDtoCollection(getArtistDao().findArtistByName(name));
    }

    private List<ArtistDto> getMappedArtistDtoCollection(List<Artist> artists) {
        List<ArtistDto> mappedCollection = new ArrayList<>();
        for (Artist artist : artists) {
            mappedCollection.add(getDozerBeanMapper().map(artist, ArtistDto.class));
        }
        return mappedCollection;
    }
}
