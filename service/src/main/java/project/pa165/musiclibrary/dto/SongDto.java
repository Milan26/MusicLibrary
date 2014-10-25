package project.pa165.musiclibrary.dto;

import project.pa165.musiclibrary.entities.Genre;


/**
 * @author Milan
 */
public class SongDto {

    private Long id;
    private String title;
    private Short trackNumber;
    private Integer length;
    private Genre genre;
    private Integer bitrate;
    private String note;
    private ArtistDto artistDto;
    private AlbumDto albumDto;

    public SongDto() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Short getTrackNumber() {
        return trackNumber;
    }

    public Integer getLength() {
        return length;
    }

    public Genre getGenre() {
        return genre;
    }

    public Integer getBitrate() {
        return bitrate;
    }

    public String getNote() {
        return note;
    }

    public ArtistDto getArtistDto() {
        return artistDto;
    }

    public AlbumDto getAlbumDto() {
        return albumDto;
    }
}
