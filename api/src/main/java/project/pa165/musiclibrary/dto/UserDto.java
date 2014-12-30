package project.pa165.musiclibrary.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import project.pa165.musiclibrary.entities.UserAuthority;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Milan
 */
public class UserDto implements Serializable {

    private static final long serialVersionUID = 54648787984L;

    private Long id;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Size(max = 40)
    private String firstName;
    @Size(max = 40)
    private String lastName;
    @Size(min = 6, max = 26)
    private String password;
    private Set<UserAuthority> userAuthorities;
    private Boolean enabled;

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserAuthority> getUserAuthorities() {
        return userAuthorities;
    }

    public void setUserAuthorities(Set<UserAuthority> userAuthorities) {
        this.userAuthorities = userAuthorities;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
