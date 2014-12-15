package project.pa165.musiclibrary.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.validator.constraints.NotEmpty;
import project.pa165.musiclibrary.util.Date;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @author Milan
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlbumDto implements Serializable {

    private static final long serialVersionUID = 2148461549153L;

    private Long id;
    @NotEmpty
    private String title;
    @NotEmpty
    @Date
    private String releaseDate;
    private String coverArt;
    @Size(max = 255)
    private String note;
    private List<SongDto> songs;

    public AlbumDto() {
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCoverArt() {
        return coverArt;
    }

    public void setCoverArt(String coverArt) {
        this.coverArt = coverArt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<SongDto> getSongs() {
        return songs;
    }

    public void setSongs(List<SongDto> songs) {
        this.songs = songs;
    }
}

