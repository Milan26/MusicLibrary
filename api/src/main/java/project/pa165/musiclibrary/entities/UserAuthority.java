package project.pa165.musiclibrary.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Milan
 */
@Entity
public class UserAuthority implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "email", referencedColumnName = "email", nullable = false)
    private User user;

    @Column(nullable = false)
    private String authority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String role) {
        this.authority = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAuthority)) return false;

        UserAuthority userAuthority = (UserAuthority) o;

        return id.equals(userAuthority.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
