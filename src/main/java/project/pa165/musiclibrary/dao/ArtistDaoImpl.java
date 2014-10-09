package project.pa165.musiclibrary.dao;

import org.springframework.stereotype.Repository;
import project.pa165.musiclibrary.entities.Artist;

@Repository
public class ArtistDaoImpl extends AbstractGenericDaoImpl<Artist> implements ArtistDao {

    public ArtistDaoImpl() {
        setType(Artist.class);
    }

}
