package project.pa165.musiclibrary.dao;

import org.springframework.stereotype.Repository;
import project.pa165.musiclibrary.entities.Song;

@Repository
public class SongDaoImpl extends GenericDaoImpl<Song> implements SongDao {

    public SongDaoImpl() {
        setType(Song.class);
    }

}
