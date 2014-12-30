package project.pa165.musiclibrary.services;

import org.springframework.security.access.prepost.PreAuthorize;
import project.pa165.musiclibrary.dto.UserDto;

import java.util.List;

/**
 * @author Martin
 */
public interface UserService {

    /**
     * Insert given userDto.
     *
     * @param userDto userDto to be inserted
     */
    void createUser(UserDto userDto);

    /**
     * Delete user by given id.
     *
     * @param id identification of user to be deleted
     */
    @PreAuthorize("hasAuthority('DELETE')")
    void deleteUser(Long id);

    /**
     * Update given userDto.
     *
     * @param userDto userDto to be updated
     */
    @PreAuthorize("authentication.principal.username.equals(#userDto.email) or hasAuthority('EDIT')")
    void updateUser(UserDto userDto);

    /**
     * Get user by given id.
     *
     * @param id identification of user to be found
     * @return found user
     */
    @PreAuthorize("hasAuthority('VIEW')")
    UserDto findUser(Long id);

    /**
     * Get all users.
     *
     * @return list of all users
     */
    @PreAuthorize("hasAuthority('VIEW')")
    List<UserDto> getAllUsers();

    /**
     *
     * @param email
     * @return
     */
    UserDto findUserByEmail(String email);
}
