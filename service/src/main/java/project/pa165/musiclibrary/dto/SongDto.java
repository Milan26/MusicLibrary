package project.pa165.musiclibrary.dto;

import project.pa165.musiclibrary.entities.Genre;


/**
 * @author Alex
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTrackNumber(Short trackNumber) {
        this.trackNumber = trackNumber;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setBitrate(Integer bitrate) {
        this.bitrate = bitrate;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setArtistDto(ArtistDto artistDto) {
        this.artistDto = artistDto;
    }

    public void setAlbumDto(AlbumDto albumDto) {
        this.albumDto = albumDto;
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
