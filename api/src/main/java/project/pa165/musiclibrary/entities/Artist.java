package project.pa165.musiclibrary.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Artist Entity.
 *
 * @author Milan
 */
@Entity
public class Artist implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String alias;

    @Column
    private String note;

    @OneToMany(mappedBy = "artist", cascade = {CascadeType.REMOVE})
    private List<Song> songs;

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

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Artist)) {
            return false;
        }
        Artist other = (Artist) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Artist{");
        sb.append("id=").append(id);
        sb.append(", alias='").append(alias).append('\'');
        sb.append(", note='").append(note).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
