package project.pa165.musiclibrary.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @author Milan
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArtistDto implements Serializable {

    private static final long serialVersionUID = 4564548431L;

    private Long id;
    @NotEmpty
    private String alias;
    @Size(max = 255)
    private String note;
    @JsonIgnore
    private List<SongDto> songs;

    public ArtistDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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
