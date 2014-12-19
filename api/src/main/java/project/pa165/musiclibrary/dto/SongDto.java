package project.pa165.musiclibrary.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.validator.constraints.NotEmpty;
import project.pa165.musiclibrary.util.Genre;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Alex
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SongDto implements Serializable {

    private static final long serialVersionUID = 11478481165L;

    private Long id;
    @NotEmpty
    private String title;
    @NotNull
    @Digits(integer = 2, fraction = 0)
    @DecimalMin(value = "1")
    private Short trackNumber;
    @NotNull
    @Digits(integer = 4, fraction = 0)
    @DecimalMin(value = "1")
    private Integer duration;
    @NotNull
    private Genre genre;
    @Digits(integer = 4, fraction = 0)
    @DecimalMin(value = "1")
    private Integer bitrate;
    @Size(max = 255)
    private String note;
    @Valid
    private ArtistDto artist;
    @Valid
    private AlbumDto album;

    public SongDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Short getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(Short trackNumber) {
        this.trackNumber = trackNumber;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Integer getBitrate() {
        return bitrate;
    }

    public void setBitrate(Integer bitrate) {
        this.bitrate = bitrate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ArtistDto getArtist() {
        return artist;
    }

    public void setArtist(ArtistDto artist) {
        this.artist = artist;
    }

    public AlbumDto getAlbum() {
        return album;
    }

    public void setAlbum(AlbumDto album) {
        this.album = album;
    }
}
